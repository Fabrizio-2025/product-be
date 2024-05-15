package co.ex.productbackend.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SaleDetailDTO {
    private Long id;
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
