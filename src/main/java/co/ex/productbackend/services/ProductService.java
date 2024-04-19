package co.ex.productbackend.services;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.repositories.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private static final String INVALID_NAME_ERROR = "El nombre del producto debe tener entre 4 y 250 caracteres.";
    private static final String INVALID_DESCRIPTION_ERROR = "La descripción del producto debe tener entre 5 y 250 caracteres.";
    private static final String INVALID_BRAND_ERROR = "La marca del producto debe tener entre 5 y 250 caracteres.";
    private static final String INVALID_PRICE_ERROR = "El precio del producto debe ser mayor a 0 y menor que 1000.";

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

    @Transactional
    public Product saveProduct(Product product) {
        validateProduct(product);

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


    private void validateProduct(Product product) {
        if (StringUtils.isBlank(product.getName()) || product.getName().trim().length() < 4 || product.getName().trim().length() > 250) {
            throw new IllegalArgumentException(INVALID_NAME_ERROR);
        }
        if (StringUtils.isBlank(product.getDescription()) || product.getDescription().trim().length() < 5 || product.getDescription().trim().length() > 250) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_ERROR);
        }
        if (StringUtils.isBlank(product.getBrand()) || product.getBrand().trim().length() < 5 || product.getBrand().trim().length() > 250) {
            throw new IllegalArgumentException(INVALID_BRAND_ERROR);
        }
        if (product.getPrice() == null ||
                product.getPrice().compareTo(BigDecimal.ZERO) <= 0 ||
                product.getPrice().compareTo(new BigDecimal("1000")) >= 0) {
            throw new IllegalArgumentException(INVALID_PRICE_ERROR);
        }
    }
}
