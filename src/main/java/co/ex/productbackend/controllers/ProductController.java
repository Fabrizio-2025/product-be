package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = convertToEntity(productDTO);
            Product createdProduct = productService.saveProduct(product);
            return ResponseEntity.ok(convertToDto(createdProduct));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product.setId(id);
        Product updatedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(convertToDto(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchp")
    public ResponseEntity<List<ProductDTO>> getProductsByPrice(@RequestParam Integer price) {
        List<ProductDTO> productDTOs = productService.findProductsByPrice(price).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/searchb")
    public ResponseEntity<List<ProductDTO>> getProductsByBrand(@RequestParam String brand) {
        List<ProductDTO> productDTOs = productService.findProductsByBrand(brand).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    private ProductDTO convertToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getBrand(), product.getPrice());
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getBrand(), productDTO.getPrice());
    }


}
