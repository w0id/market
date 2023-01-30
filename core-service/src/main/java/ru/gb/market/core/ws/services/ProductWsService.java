package ru.gb.market.core.ws.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.Product;
import ru.gb.market.core.ws.soap.products.ProductWs;
import ru.gb.market.core.ws.repositories.IProductWsRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductWsService {

    private IProductWsRepository productWsRepository;

    public static final Function<Product, ProductWs> functionEntityToSoap = p -> {
        ProductWs pw = new ProductWs();
        pw.setId(p.getId());
        pw.setName(p.getName());
        pw.setCost(p.getCost());
        return pw;
    };

    public List<ProductWs> getAllProducts() {
        return productWsRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductWs getById(Long id) {
        return productWsRepository.findById(id).map(functionEntityToSoap).get();
    }
}
