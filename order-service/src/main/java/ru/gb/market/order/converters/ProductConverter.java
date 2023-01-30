package ru.gb.market.order.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.order.api.ProductDto;
import ru.gb.market.order.data.Product;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        return product;
    }
}
