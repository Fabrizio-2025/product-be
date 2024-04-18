package co.ex.productbackend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Standard constructors, getters, and setters

    public Sale() {
    }

    public Sale(LocalDate date) {
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

    // ToString, equals, and hashCode methods can be added as necessary

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
