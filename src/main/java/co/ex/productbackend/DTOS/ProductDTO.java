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
    @Size(min = 3, max = 70, message = "El tamaño minimo del nombres es tres y el maximo es 70")
    private String name;
    @Size(min = 3, max = 255, message = "El tamaño minimo de la descripcion es tres y el maximo es 255")
    private String description;
    @Size(min = 3, max = 70, message = "El tamaño minimo de la marca es tres y el maximo es 70")
    private String brand;
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal price;


}
