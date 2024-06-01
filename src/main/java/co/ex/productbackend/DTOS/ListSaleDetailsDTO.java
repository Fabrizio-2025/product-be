package co.ex.productbackend.DTOS;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ListSaleDetailsDTO {
    private Long productId;
    private Integer quantity;
}
