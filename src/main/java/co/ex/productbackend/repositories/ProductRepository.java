package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends GenericRepo<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByPrice(Integer price);

    List<Product> findByBrand(String brand);

}
