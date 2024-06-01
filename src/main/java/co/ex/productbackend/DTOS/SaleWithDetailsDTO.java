package co.ex.productbackend.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private List<SaleDetailDTO> saleDetails;
}
