package co.ex.productbackend.DTOS;

import java.math.BigDecimal;

public class SaleDetailDTO {
    private Long id;
    private Long saleId; // ID de la venta asociada
    private Long productId; // ID del producto
    private String productName; // Nombre del producto para mostrar en el front-end, si es necesario
    private BigDecimal productPrice; // Precio unitario del producto
    private Integer quantity; // Cantidad vendida del producto
    private BigDecimal salePrice; // Precio de venta del producto (podría ser diferente al precio unitario)


    // Constructores, getters y setters

    public SaleDetailDTO() {
    }

    public SaleDetailDTO(Long id, Long saleId, Long productId, String productName, BigDecimal productPrice, Integer quantity, BigDecimal salePrice) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    // toString, equals, y hashCode pueden ser añadidos si son necesarios

    @Override
    public String toString() {
        return "SaleDetailDTO{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", salePrice=" + salePrice +
                '}';
    }
}
