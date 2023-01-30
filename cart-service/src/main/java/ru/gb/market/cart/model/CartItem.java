package ru.gb.market.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long id;
    private String productName;
    private int quantity;
    private BigDecimal costPerProduct;
    private BigDecimal cost;
}
