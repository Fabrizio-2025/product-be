package co.ex.productbackend.DTOS;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter
public class SaleWithDetailsDTO {
    private LocalDate date;
    private List<SaleDetailDTO> saleDetails;
}
