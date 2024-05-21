package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.exception.ModeloNotFoundException;
import co.ex.productbackend.response.ProductResponse;
import co.ex.productbackend.services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<?> listar() throws Exception {
        List<ProductDTO> lista = service.listar().stream().map(p -> mapper.map(p, ProductDTO.class)).collect(Collectors.toList());
        if (lista.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No products Samurai");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable("id") Long id) throws Exception {
        Product obj = service.listarPorId(id);
        if (obj == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "The product does not exist, Samurai.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        ProductDTO dtoResponse = mapper.map(obj, ProductDTO.class);
        String formattedId = String.format("PROD-%02d", dtoResponse.getId());

        ProductResponse response = new ProductResponse(
                formattedId,
                dtoResponse.getName(),
                dtoResponse.getDescription(),
                dtoResponse.getBrand(),
                dtoResponse.getPrice()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/brand")
    public ResponseEntity<?> listByBrand(@RequestParam("brand") String brand) throws Exception {
        List<Product> products = service.listByBrand(brand);
        List<ProductDTO> dtoList = products.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        if (dtoList.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No products found for the brand: " + brand + ", Samurai.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@Valid @RequestBody ProductDTO dtoRequest) throws Exception {
        Product p = mapper.map(dtoRequest, Product.class);
        Product obj = service.registrar(p);
        ProductDTO dtoResponse = mapper.map(obj, ProductDTO.class);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product created successfully, Samurai.");
        response.put("product", dtoResponse);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> Modificar(@Valid @RequestBody ProductDTO dtoRequest) throws Exception {
        Product product = service.listarPorId((dtoRequest.getId()));
        if (product == null) {
            throw new ModeloNotFoundException("ID not found " + dtoRequest.getId() + ", Samurai.");
        }
        Product p = mapper.map(dtoRequest, Product.class);

        Product obj = service.modificar(p);

        ProductDTO dtoResponse = mapper.map(obj, ProductDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
        Product obj = service.listarPorId(id);
        if (obj == null) {
            throw new ModeloNotFoundException("ID not found " + id + ", Samurai.");
        }
        try {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            throw new Exception("Cannot delete product with associated sales", e);
        }
    }

}
