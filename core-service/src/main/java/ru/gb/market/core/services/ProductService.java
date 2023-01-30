package ru.gb.market.core.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.Product;
import ru.gb.market.core.repositories.IProductRepository;
import ru.gb.market.core.repositories.specifications.ProductsSpecifications;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private IProductRepository productRepository;

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getProductFilter(BigDecimal min, BigDecimal max, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (null != min) {
            spec = spec.and(ProductsSpecifications.costGreaterThenOrEqualsThen(min));
        }
        if (null != max) {
            spec = spec.and(ProductsSpecifications.costLessThenOrEqualsThen(max));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by("id").ascending()));
    }
}
