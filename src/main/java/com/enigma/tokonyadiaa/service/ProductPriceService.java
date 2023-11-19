package com.enigma.tokonyadiaa.service;

import com.enigma.tokonyadiaa.entity.ProductPrice;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);
    ProductPrice findProductPriceActive(String productId,boolean active);

}
