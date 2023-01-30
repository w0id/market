package ru.gb.market.core.controllers;

import api.ProductDto;
import api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.converters.ProductConverter;
import ru.gb.market.core.data.Product;
import ru.gb.market.core.services.ProductService;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
        return new ProductDto(product.getId(), product.getName(), product.getCost());
    }

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(value = "min",required = false) BigDecimal min,
            @RequestParam(value = "max",required = false) BigDecimal max,
            @RequestParam(value = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.getProductFilter(min, max, page).map(
                productConverter::entityToDto
        );
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        productService.save(new Product(productDto.getName(), productDto.getCost()));
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void delProduct(@PathVariable Long id) {
        productService.delProduct(id);
    }

    @PutMapping
    public ProductDto changeCost(@RequestBody ProductDto productDto) {
        Product product = productService.getProduct(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productDto.getId() + " не найден"));
        product.setCost(productDto.getCost());
        productService.save(product);
        return productDto;
    }
}