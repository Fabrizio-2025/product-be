package co.ex.productbackend.DTOS;

import lombok.Data;

@Data
public class WareHouseDTO {
    private Long id;
    private Long productId;
    private int quantity;
}
