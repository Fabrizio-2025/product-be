package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.DTOS.SaleDetailDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.services.ProductService;
import co.ex.productbackend.services.SaleDetailService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

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
    public ResponseEntity<SaleDetailDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
        SaleDetail obj = service.listarPorId(id);
        SaleDetailDTO dtoResponse = mapper.map(obj, SaleDetailDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<ProductDTO>> listProductsBySaleId(@PathVariable("saleId") Long saleId) throws Exception {
        List<Product> products = service.listProductsBySaleId(saleId);
        List<ProductDTO> dtoList = products.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/sale/{saleId}/total-price")
    public ResponseEntity<BigDecimal> getTotalPriceBySaleId(@PathVariable("saleId") Long saleId) throws Exception {
        BigDecimal totalPrice = service.calculateTotalPriceBySaleId(saleId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDetailDTO> registrar(@Valid @RequestBody SaleDetailDTO dto) throws Exception {
        SaleDetail saleDetail = mapper.map(dto, SaleDetail.class);

        // Obtener el producto asociado
        Product product = productService.listarPorId(saleDetail.getProduct().getId());
        if (product == null) {
            throw new Exception("Product not found");
        }

        // Calcular el precio total
        saleDetail.setPrice(product.getPrice().multiply(BigDecimal.valueOf(saleDetail.getQuantity())));

        SaleDetail obj = service.registrar(saleDetail);
        SaleDetailDTO dtoResponse = mapper.map(obj, SaleDetailDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
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
