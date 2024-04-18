package co.ex.productbackend.services;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalStateException("Ya existe un producto con el nombre: " + product.getName());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProductsByPrice(Integer price) {
        return productRepository.findByPrice(price);
    }

    public List<Product> findProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }


}
