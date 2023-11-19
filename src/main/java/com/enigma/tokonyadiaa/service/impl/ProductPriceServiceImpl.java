package com.enigma.tokonyadiaa.service.impl;

import com.enigma.tokonyadiaa.entity.ProductPrice;
import com.enigma.tokonyadiaa.repository.ProductPriceRepository;
import com.enigma.tokonyadiaa.service.ProductPriceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private final ProductPriceRepository productPriceRepository;
    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {
        return productPriceRepository.findById(id).get();
    }

    @Override
    public ProductPrice findProductPriceActive(String productId, boolean active) {
        return productPriceRepository.findByProduct_idAndIsActive(productId,active).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"product not found"));
    }
}
