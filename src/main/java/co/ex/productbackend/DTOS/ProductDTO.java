package co.ex.productbackend.DTOS;



import jakarta.validation.constraints.*;


import java.math.BigDecimal;

public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, String brand, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    // Getters and setters for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // ToString, equals, and hashCode methods can be added for better logging and performance
}
