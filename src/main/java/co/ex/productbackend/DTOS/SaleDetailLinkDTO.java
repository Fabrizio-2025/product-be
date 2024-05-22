package co.ex.productbackend.DTOS;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@Getter
@Setter
public class SaleDetailLinkDTO extends RepresentationModel<SaleDetailLinkDTO> {
    private Long id;
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
