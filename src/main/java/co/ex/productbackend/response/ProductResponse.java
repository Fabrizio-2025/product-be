package co.ex.productbackend.response;

import lombok.*;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
}
