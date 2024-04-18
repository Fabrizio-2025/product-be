package co.ex.productbackend.DTOS;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleDTO {
    private Long id;
    private LocalDate date;


    // Constructors

    public SaleDTO() {
    }

    public SaleDTO(Long id, LocalDate date) {
        this.id = id;
        this.date = date;

    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    // toString, equals, and hashCode methods can be added if necessary

    @Override
    public String toString() {
        return "SaleDTO{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
