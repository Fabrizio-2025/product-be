package co.ex.productbackend.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SaleDetail")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Sale", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Product", nullable = false)
    private Product product; // Referencia a la entidad Product

    @Column(name = "Cantidad", nullable = false)
    private Integer quantity;

    @Column(name = "PrecioVenta", nullable = false)
    private BigDecimal price;

    // Constructors, getters, setters, and other methods

    public SaleDetail() {
    }

    public SaleDetail(Sale sale, Product product, Integer quantity, BigDecimal price) {
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // ToString, equals, and hashCode methods

    @Override
    public String toString() {
        return "SaleDetail{" +
                "id=" + id +
                ", sale=" + (sale != null ? sale.getId() : "null") +
                ", product=" + (product != null ? product.getId() : "null") +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
