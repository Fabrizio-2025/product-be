package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.ProductImage;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.repositories.ProductImageRepository;
import co.ex.productbackend.repositories.ProductRepository;
import co.ex.productbackend.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductImageServiceImpl extends CRUDImplementation<ProductImage, Long> implements ProductImageService {
    @Autowired
    private ProductImageRepository repo;

    @Autowired
    private ProductRepository productRepo;

    @Override
    protected GenericRepo<ProductImage, Long> getRepo() {
        return repo;
    }
    @Override
    public ProductImage saveImage(Long productId, MultipartFile file) throws IOException {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setImageData(file.getBytes());
        image.setCreatedAt(LocalDateTime.now());
        return repo.save(image);
    }

    @Override
    public Optional<ProductImage> getImage(Long id) {
        return repo.findById(id);
    }

    @Override
    public ProductImage updateImage(Long productId, MultipartFile file) throws IOException {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<ProductImage> existingImageOpt = repo.findByProductId(productId);
        ProductImage image;
        if (existingImageOpt.isPresent()) {
            image = existingImageOpt.get();
        } else {
            image = new ProductImage();
            image.setProduct(product);
            image.setCreatedAt(LocalDateTime.now());
        }
        image.setImageData(file.getBytes());
        return repo.save(image);
    }
}
