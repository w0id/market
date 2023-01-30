package ru.gb.market.core.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.gb.market.core.data.Product;

public interface IProductWsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
