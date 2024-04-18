package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByPrice(Integer price);

    // O por marca
    List<Product> findByBrand(String brand);
}
