package co.ex.productbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Entity
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private BigDecimal price;

    // Standard constructors, getters and setters

    public Product() {
    }

    public Product(String name, String description, String brand, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    // Getters and setters for each field

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStock(BigDecimal stock) {
        this.price = stock;
    }

    // ToString method is optional but can be handy for logging

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
