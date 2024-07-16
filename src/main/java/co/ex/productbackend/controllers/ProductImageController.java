package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductImageDTO;
import co.ex.productbackend.entities.ProductImage;
import co.ex.productbackend.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Base64;
import java.util.Optional;
@RestController
@RequestMapping("/imagenes")
public class ProductImageController {
    @Autowired
    private ProductImageService service;

    @PostMapping("/upload")
    public ResponseEntity<ProductImageDTO> uploadImage(@RequestParam("productId") Long productId,
                                                       @RequestParam("file") MultipartFile file) throws Exception {
        ProductImage image = service.saveImage(productId, file);
        ProductImageDTO imageDTO = new ProductImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setProductId(image.getProduct().getId());
        imageDTO.setImageData(Base64.getEncoder().encodeToString(image.getImageData()));
        imageDTO.setCreatedAt(image.getCreatedAt());
        return ResponseEntity.ok(imageDTO);
    }

    @GetMapping("media/{id}")
    public ResponseEntity<byte[]> getImagesmedia(@PathVariable Long id) {
        Optional<ProductImage> imageOptional = service.getImage(id);
        if (imageOptional.isPresent()) {
            ProductImage image = imageOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getImageData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductImageDTO> getImage(@PathVariable Long id) {
        Optional<ProductImage> imageOptional = service.getImage(id);
        if (imageOptional.isPresent()) {
            ProductImage image = imageOptional.get();
            ProductImageDTO imageDTO = new ProductImageDTO();
            imageDTO.setId(image.getId());
            imageDTO.setProductId(image.getProduct().getId());
            imageDTO.setImageData(Base64.getEncoder().encodeToString(image.getImageData()));
            imageDTO.setCreatedAt(image.getCreatedAt());
            return ResponseEntity.ok(imageDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
