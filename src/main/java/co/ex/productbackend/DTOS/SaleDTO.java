package co.ex.productbackend.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SaleDTO {
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "The date must be in the past or present")
    private LocalDate date;


}
