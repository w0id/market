package ru.gb.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.cart.api.CartDto;
import ru.gb.market.cart.api.StringResponse;
import ru.gb.market.cart.converters.CartConverter;
import ru.gb.market.cart.services.CartService;


@RestController
@RequestMapping("/api/v1/cart_items")
@RequiredArgsConstructor
public class CartController {

    private final CartConverter cartConverter;
    private final CartService cartService;

    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }

    @GetMapping("/merge")
    public void mergeCarts(@RequestHeader(name = "username", required = false) String username, @RequestParam String uuid) {
        cartService.merge(username, uuid);
    }

    @GetMapping("/generate_uuid")
    public StringResponse generateUUid() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping
    public CartDto getCartItems(@RequestHeader(name = "username", required = false) String username, @RequestParam String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @PostMapping
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @RequestParam String uuid, @RequestParam Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }

    @PutMapping
    public void changeQuantity(@RequestHeader(name = "username", required = false) String username, @RequestParam String uuid, @RequestParam Long id,
                               @RequestParam int quantity) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.changeQuantity(targetUuid, id, quantity);
    }

    @DeleteMapping
    public void deleteFromCart(@RequestHeader(name = "username", required = false) String username, @RequestParam String uuid, @RequestParam Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.delete(targetUuid, id);
    }
}