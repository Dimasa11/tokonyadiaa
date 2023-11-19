package com.enigma.tokonyadiaa.service.impl;

import com.enigma.tokonyadiaa.entity.Product;
import com.enigma.tokonyadiaa.entity.ProductPrice;
import com.enigma.tokonyadiaa.entity.Store;
import com.enigma.tokonyadiaa.model.request.ProductRequest;
import com.enigma.tokonyadiaa.model.response.ProductResponse;
import com.enigma.tokonyadiaa.repository.ProductRepository;
import com.enigma.tokonyadiaa.service.ProductPriceService;
import com.enigma.tokonyadiaa.service.ProductService;
import com.enigma.tokonyadiaa.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*@SpringBootTest
@RequiredArgsConstructor
class ProductServiceImplTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final ProductPriceService productPriceService = mock(ProductPriceService.class);

    private  final StoreService storeService= mock(StoreService.class);


    private final ProductService productService = new ProductServiceImpl(productRepository, storeService, productPriceService);

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void createProductt() {
        //data dummy
        //data dummy request
        ProductRequest dummyRequest = new ProductRequest();
        dummyRequest.setProductId("product1");
        dummyRequest.setStoreId("store1");
        dummyRequest.setProductName("oreo");
        dummyRequest.setDescription("hitam enak");
        dummyRequest.setPrice(100L);
        dummyRequest.setStock(100);

        //data dummy Store
        Store dummyStore = new Store();
        dummyStore.setId("123w");
        dummyStore.setName("toko berkah");
        dummyStore.setAddress("jakarta");


        //data dummy Product
        Product dummyProduct = new Product();
        dummyProduct.setId(dummyRequest.getProductId());
        dummyProduct.setName(dummyRequest.getProductName());
        dummyProduct.setDescription(dummyRequest.getDescription());


        //data dummy productPrice
        ProductPrice dummyProductPrice = new ProductPrice();
        dummyProductPrice.setPrice(dummyRequest.getPrice());
        dummyProductPrice.setStock(dummyRequest.getStock());

        //mock
        when(storeService.getById(dummyRequest.getStoreId())).thenReturn(dummyStore);
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(dummyProduct);
        when(productPriceService.create(any(ProductPrice.class))).thenReturn(dummyProductPrice);

        //perform
        ProductResponse actualProduct = productService.createProductt(dummyRequest);

        //verify
        verify(storeService,times(1)).getById(dummyRequest.getStoreId());
        verify(productRepository,times(1)).saveAndFlush(any(Product.class));
        verify(productPriceService,times(1)).create(any(ProductPrice.class));

        //assert
        assertEquals(dummyProduct.getName(),actualProduct.getProductName());
        assertEquals(dummyProductPrice.getPrice(),actualProduct.getPrice());
        assertEquals(dummyStore.getName(),actualProduct.getStore().getStoreName());



    }

    @Test
    void getAll() {
    }
}*/