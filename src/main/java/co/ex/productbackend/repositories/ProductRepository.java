package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends GenericRepo<Product, Long> {

    boolean existsById(Long id);

    List<Product> findByBrand(String brand);

    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.brand = :brand")
    List<Product> findByNameAndBrand(String name, String brand);

    @Query(value = "SELECT p.id, p.name, p.description, p.brand, p.price, SUM(sd.quantity) AS totalQuantity " +
            "FROM products p " +
            "JOIN sale_details sd ON p.id = sd.id_product " +
            "GROUP BY p.id " +
            "ORDER BY totalQuantity DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<Object[]> findTopPurchasedProducts(@Param("limit") int limit);
}
