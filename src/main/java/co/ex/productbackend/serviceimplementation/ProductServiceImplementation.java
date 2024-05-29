package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.DTOS.ProductWithTotalQuantityDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.repositories.ProductRepository;
import co.ex.productbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation extends CRUDImplementation<Product, Long> implements ProductService {
    @Autowired
    private ProductRepository repo;

    @Override
    protected GenericRepo<Product, Long> getRepo() {
        return repo;
    }

    @Override
    public List<Product> listByBrand(String brand) throws Exception {
        return repo.findByBrand(brand);
    }

    @Override
    public List<ProductWithTotalQuantityDTO> findTopPurchasedProducts(int limit) {
        List<Object[]> results = repo.findTopPurchasedProducts(limit);
        return results.stream()
                .map(result -> {
                    Long id = ((Number) result[0]).longValue();
                    String name = (String) result[1];
                    String description = (String) result[2];
                    String brand = (String) result[3];
                    BigDecimal price = (BigDecimal) result[4];
                    Long totalQuantity = ((Number) result[5]).longValue();
                    return new ProductWithTotalQuantityDTO(id, name, description, brand, price, totalQuantity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> listByNameAndBrand(String name, String brand) {
        return repo.findByNameAndBrand(name, brand);
    }

}
