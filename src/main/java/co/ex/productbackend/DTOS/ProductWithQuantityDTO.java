package co.ex.productbackend.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithQuantityDTO {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
}
