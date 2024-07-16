package co.ex.productbackend.services;

import co.ex.productbackend.entities.ProductImage;
import org.springframework.web.multipart.MultipartFile;


import java.util.Optional;

public interface ProductImageService  extends ICRUD<ProductImage, Long>{
    ProductImage saveImage(Long productId, MultipartFile file) throws Exception;
    Optional<ProductImage> getImage(Long id);
}
