package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends GenericRepo<Product, Long> {

    @Query(value = "SELECT p.* FROM products p JOIN sale_details sd ON p.id = sd.id_product GROUP BY p.id ORDER BY SUM(sd.quantity) DESC LIMIT 4", nativeQuery = true)
    List<Product> findTop4MostPurchasedProducts();

    List<Product> findByBrand(String brand);

}
