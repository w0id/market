package ru.gb.market.cart.model;

import lombok.Data;
import ru.gb.market.cart.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//TODO: Добавить поля создания/изменения во все таблицы
@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalCost;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product) {
        items.stream().filter(i -> i.getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(i -> {
//                    items.set(items.indexOf(i),new CartItem(product.getId(), product.getName(), i.getQuantity()+1, product.getCost(), product.getCost().multiply(BigDecimal.valueOf(i.getQuantity() + 1))));
                    items.set(items.indexOf(i), CartItem.builder()
                            .id(product.getId())
                            .productName(product.getName())
                            .quantity(i.getQuantity() + 1)
                            .costPerProduct(product.getCost())
                            .cost(product.getCost()
                                    .multiply(BigDecimal.valueOf(i.getQuantity() + 1)))
                            .build());
                }, () -> {
//                    items.add(new CartItem(product.getId(), product.getName(), 1, product.getCost(), product.getCost()));
                    items.add(CartItem.builder()
                            .id(product.getId())
                            .productName(product.getName())
                            .quantity(1)
                            .costPerProduct(product.getCost())
                            .cost(product.getCost())
                            .build());
                });
        recalculate();
    }

    public void delete(ProductDto product) {
        items.removeIf(cartItem -> product.getId().equals(cartItem.getId()));
        recalculate();
    }

    private void recalculate() {
        totalCost = new BigDecimal(0);
        items.forEach(cartItem -> totalCost = totalCost.add(cartItem.getCost()));
    }

    public void change(final ProductDto product, final int quantity) {
        items.stream().filter(i -> i.getId().equals(product.getId()))
                .findFirst()
                .ifPresent(i -> {
                    if ((i.getQuantity() + quantity)==0) {
                        items.remove(items.indexOf(i));
                    } else {
//                        items.set(items.indexOf(i),new CartItem(product.getId(), product.getName(), i.getQuantity()+quantity, product.getCost(), product.getCost().multiply(BigDecimal.valueOf(i.getQuantity() + quantity))));
                        items.set(items.indexOf(i), CartItem.builder()
                                .id(product.getId())
                                .productName(product.getName())
                                .quantity(i.getQuantity() + quantity)
                                .costPerProduct(product.getCost())
                                .cost(product.getCost()
                                        .multiply(BigDecimal.valueOf(i.getQuantity() + quantity)))
                                .build());
                    }
                });
        recalculate();
    }

    public void merge(Cart another) {
        for (CartItem anotherItem : another.items) {
            boolean merged = false;
            for (CartItem item : items) {
                if (item.getId().equals(anotherItem.getId())) {
                    item.setQuantity(item.getQuantity() + anotherItem.getQuantity());
                    item.setCost(item.getCostPerProduct().multiply(BigDecimal.valueOf(item.getQuantity())));
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }

    public void clear() {
        items.clear();
        recalculate();
    }
}
