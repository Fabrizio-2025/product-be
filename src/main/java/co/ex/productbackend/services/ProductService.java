package co.ex.productbackend.services;

import co.ex.productbackend.DTOS.ProductWithTotalQuantityDTO;
import co.ex.productbackend.entities.Product;

import java.util.List;


public interface ProductService extends ICRUD<Product, Long> {
    List<Product> listByBrand(String brand) throws Exception;

    List<Product> listByNameAndBrand(String name, String brand);

    List<ProductWithTotalQuantityDTO> findTopPurchasedProducts(int limit);

    boolean existsById(Long id);
}
