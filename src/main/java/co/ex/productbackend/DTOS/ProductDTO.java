package co.ex.productbackend.DTOS;



import jakarta.validation.constraints.*;

import lombok.*;


import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDTO {

    private Long id;
    @Size(min = 3, max = 70, message = "The size of the name must be between 3 and 70 characters")
    private String name;
    @Size(min = 3, max = 255, message = "The size of the description must be between 3 and 255 characters")
    private String description;
    @Size(min = 3, max = 70, message = "The size of the brand must be between 3 and 70 characters")
    private String brand;
    @DecimalMin(value = "0.00", message = "The price must be greater than 0")
    private BigDecimal price;


}
