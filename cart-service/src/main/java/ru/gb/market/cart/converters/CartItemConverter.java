package ru.gb.market.cart.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.cart.api.CartItemDto;
import ru.gb.market.cart.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductName(cartItem.getProductName());
        cartItemDto.setCost(cartItem.getCost());
        cartItemDto.setCostPerProduct(cartItem.getCostPerProduct());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }
}
