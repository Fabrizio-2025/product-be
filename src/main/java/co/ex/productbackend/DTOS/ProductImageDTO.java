package co.ex.productbackend.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductImageDTO {
    private Long id;
    private Long productId;
    private String imageData; // Base64 encoded string
    private LocalDateTime createdAt;
}
