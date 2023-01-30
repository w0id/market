package ru.gb.market.core.ws.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.market.core.ws.soap.products.GetAllProductsRequest;
import ru.gb.market.core.ws.soap.products.GetAllProductsResponse;
import ru.gb.market.core.ws.soap.products.GetProductByIdRequest;
import ru.gb.market.core.ws.soap.products.GetProductByIdResponse;
import ru.gb.market.core.ws.services.ProductWsService;



@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.w0id.com/ws/products";
    private final ProductWsService productWsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productWsService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productWsService.getById(request.getId()));
        return response;
    }
}
