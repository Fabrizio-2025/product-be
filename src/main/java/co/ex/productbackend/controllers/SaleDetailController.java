package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.DTOS.ProductWithQuantityDTO;
import co.ex.productbackend.DTOS.SaleDetailDTO;
import co.ex.productbackend.DTOS.SaleDetailLinkDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.exception.ModeloNotFoundException;
import co.ex.productbackend.services.ProductService;
import co.ex.productbackend.services.SaleDetailService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {

    @Autowired
    private SaleDetailService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDetailDTO>> listar() throws Exception {
        List<SaleDetailDTO> lista = service.listar().stream().map(p -> mapper.map(p, SaleDetailDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailLinkDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
        SaleDetail obj = service.listarPorId(id);
        if (obj == null) {
            throw new ModeloNotFoundException("ID not found " + id);
        }

        SaleDetailLinkDTO resource = convertToResource(obj);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    private SaleDetailLinkDTO convertToResource(SaleDetail saleDetail) {
        SaleDetailLinkDTO resource = mapper.map(saleDetail, SaleDetailLinkDTO.class);

        try {
            // Agregar enlace a este SaleDetail
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(SaleDetailController.class).listarPorId(saleDetail.getId())).withSelfRel());

            // Agregar enlace a la Sale asociada
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(SaleController.class).listarPorId(saleDetail.getSale().getId())).withRel("sale"));

            // Agregar enlace al Product asociado
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(ProductController.class).listarPorId(saleDetail.getProduct().getId())).withRel("product"));
        } catch (Exception e) {
            // Manejar excepciones según sea necesario
            e.printStackTrace();
        }

        return resource;
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<ProductWithQuantityDTO>> listProductsBySaleId(@PathVariable("saleId") Long saleId) throws Exception {
        List<SaleDetail> saleDetails = service.findBySaleId(saleId);

        List<ProductWithQuantityDTO> dtoList = saleDetails.stream().map(saleDetail -> {
            Product product = saleDetail.getProduct();
            ProductWithQuantityDTO dto = mapper.map(product, ProductWithQuantityDTO.class);
            dto.setQuantity(saleDetail.getQuantity());
            return dto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/sale/{saleId}/total-price")
    public ResponseEntity<BigDecimal> getTotalPriceBySaleId(@PathVariable("saleId") Long saleId) throws Exception {
        BigDecimal totalPrice = service.calculateTotalPriceBySaleId(saleId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDetailLinkDTO> registrar(@Valid @RequestBody SaleDetailDTO dto) throws Exception {
        SaleDetail saleDetail = mapper.map(dto, SaleDetail.class);

        // Obtener el producto asociado
        Product product = productService.listarPorId(saleDetail.getProduct().getId());
        if (product == null) {
            throw new Exception("Product not found");
        }

        // Calcular el precio total
        saleDetail.setPrice(product.getPrice().multiply(BigDecimal.valueOf(saleDetail.getQuantity())));

        SaleDetail obj = service.registrar(saleDetail);
        SaleDetailLinkDTO resource = convertToResource(obj);

        // Construcción del URI del recurso creado
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(location).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> modificar(@Valid @RequestBody SaleDetailDTO dto, @PathVariable("id") Long id) throws Exception {
        SaleDetail saleDetail = mapper.map(dto, SaleDetail.class);
        saleDetail.setId(id);

        // Obtener el producto asociado
        Product product = productService.listarPorId(saleDetail.getProduct().getId());
        if (product == null) {
            throw new Exception("Product not found");
        }

        // Calcular el precio total
        saleDetail.setPrice(product.getPrice().multiply(BigDecimal.valueOf(saleDetail.getQuantity())));

        SaleDetail obj = service.modificar(saleDetail);
        SaleDetailDTO dtoResponse = mapper.map(obj, SaleDetailDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
