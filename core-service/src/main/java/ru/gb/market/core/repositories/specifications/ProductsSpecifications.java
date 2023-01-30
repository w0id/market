package ru.gb.market.core.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.market.core.data.Product;

import java.math.BigDecimal;

public class ProductsSpecifications {
    public static Specification<Product> costGreaterThenOrEqualsThen(BigDecimal min) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), min);
    }

    public static Specification<Product> costLessThenOrEqualsThen(BigDecimal  max) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), max);
    }
}
