package com.enigma.tokonyadiaa.service;

import com.enigma.tokonyadiaa.entity.Product;
import com.enigma.tokonyadiaa.model.request.ProductRequest;
import com.enigma.tokonyadiaa.model.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    ProductResponse createProductt(ProductRequest request);
    List<ProductResponse> getAll();
    Page<ProductResponse> getAllByNameOrPrice(String name , Long maxPrice , Integer page , Integer size);

}
