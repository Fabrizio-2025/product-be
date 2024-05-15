package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends GenericRepo<Product, Long> {


    List<Product> findByBrand(String brand);

}
