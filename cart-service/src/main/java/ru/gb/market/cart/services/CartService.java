package ru.gb.market.cart.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.market.cart.api.ProductDto;
import ru.gb.market.cart.integrations.ProductServiceIntegration;
import ru.gb.market.cart.model.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long id) {
        ProductDto product = productServiceIntegration.getProductById(id);
        execute(uuid, cart -> cart.add(product));
    }

    public void delete(String uuid, Long id) {
        ProductDto product = productServiceIntegration.getProductById(id);
        execute(uuid, cart -> cart.delete(product));
    }

    public void changeQuantity(String uuid, final Long id, final int quantity) {
        ProductDto product = productServiceIntegration.getProductById(id);
        execute(uuid, cart -> cart.change(product, quantity));
    }

    public void save(String uuid, Cart cart) {
        redisTemplate.opsForValue().set(getCartUuidFromSuffix(uuid), cart);
    }

    public void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(getCartUuidFromSuffix(uuid), cart);
    }

    public void merge(String userCartUuid, String guestCartUuid) {
        Cart userCart = getCurrentCart(userCartUuid);
        Cart guestCart = getCurrentCart(guestCartUuid);
        userCart.merge(guestCart);
        save(userCartUuid, userCart);
        save(guestCartUuid, guestCart);
    }

    public void cleanCart(String cartId) {
        execute(cartId, Cart::clear);
    }
}
