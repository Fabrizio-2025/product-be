package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends GenericRepo<ProductImage, Long>{
    Optional<ProductImage> findByProductId(Long productId);
}
