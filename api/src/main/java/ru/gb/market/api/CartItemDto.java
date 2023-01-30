package ru.gb.market.api;

import java.math.BigDecimal;

public class CartItemDto {
    private Long id;
    private String productName;
    private int quantity;
    private BigDecimal costPerProduct;
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(final BigDecimal costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }
}
