package ru.gb.market.core.converters;

import api.ProductDto;
import org.springframework.stereotype.Component;
import ru.gb.market.core.data.Product;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getCost());
    }
}
