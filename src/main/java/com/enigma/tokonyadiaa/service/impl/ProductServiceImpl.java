package com.enigma.tokonyadiaa.service.impl;

import com.enigma.tokonyadiaa.entity.Product;
import com.enigma.tokonyadiaa.entity.ProductPrice;
import com.enigma.tokonyadiaa.entity.Store;
import com.enigma.tokonyadiaa.exception.ResourceNotFoundException;
import com.enigma.tokonyadiaa.model.request.ProductRequest;
import com.enigma.tokonyadiaa.model.response.ProductResponse;
import com.enigma.tokonyadiaa.model.response.StoreResponse;
import com.enigma.tokonyadiaa.repository.ProductPriceRepository;
import com.enigma.tokonyadiaa.repository.ProductRepository;
import com.enigma.tokonyadiaa.service.ProductPriceService;
import com.enigma.tokonyadiaa.service.ProductService;
import com.enigma.tokonyadiaa.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private  StoreService storeService;

    private  ProductPriceService productPriceService;

    private  ProductPriceRepository productPriceRepository;



    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

   @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

   @Transactional(rollbackOn = Exception.class)
    @Override//method ini nanti akan create di 2 table,1 di product dan 2 di productPrice
    public ProductResponse createProductt(ProductRequest request) {
        Store store = storeService.getById(request.getStoreId());

        Product product = Product.builder()
                .name(request.getProductName())
                .description(request.getDescription())
                .build();
        productRepository.saveAndFlush(product);

        ProductPrice productPrice = ProductPrice.builder()
                .price(request.getPrice())
                .stock(request.getStock())
                .product(product)
                .store(store)
                .isActive(true)
                .build();
        productPriceService.create(productPrice);
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(StoreResponse.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .address(store.getAddress())
                        .noSiup(store.getNoSiup())
                        .phone(store.getPhone())
                        .build())
                .build();
    }
    @Override
    public List<ProductResponse> getAll() {
        // Get all products
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            Optional<ProductPrice> productPriceOptional = productPriceRepository.findByProduct_idAndIsActive(product.getId(), true);

            if (productPriceOptional.isPresent()) {
                ProductPrice productPrice = productPriceOptional.get();
                Store store = productPrice.getStore();

                ProductResponse productResponse = ProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getName())
                        .description(product.getDescription())
                        .price(productPrice.getPrice())
                        .stock(productPrice.getStock())
                        .store(StoreResponse.builder()
                                .id(store.getId())
                                .name(store.getName())
                                .address(store.getAddress())
                                .noSiup(store.getNoSiup())
                                .phone(store.getPhone())
                                .build())
                        .build();

                productResponses.add(productResponse);
            }
        }

        return productResponses;
    }

    private static ProductResponse toProductResponse(Store store, Product product, ProductPrice productPrice) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(StoreResponse.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .address(store.getAddress())
                        .build())
                .build();
    }

    @Override
    public Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        Specification<Product> specification = (root, query , criteriaBuilder ) ->{
            Join<Product , ProductPrice> productPrices = root.join("productPrices");
            List<Predicate> predicates = new ArrayList<>();
            if (name != null){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + name.toLowerCase() + "%"));
            }
            if (maxPrice != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(productPrices.get("price"), maxPrice));
            }
            return  query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepository.findAll(specification,pageable);
        List<ProductResponse> productResponses = new ArrayList<>(); // ini digunakan untuk menyimpan response prodcut
        for (Product product : products.getContent()){
            //for disini digunakan untuk mengiterasi daftar product yang disimpan dalam object
            Optional<ProductPrice> productPrice = product.getProductPrices()//Optonial ini mencoba untuk mencari harga yang aktif
                    .stream()
                    .filter(ProductPrice::getIsActive).findFirst();
            if (productPrice.isEmpty()) continue;//kondisi ini untuk memeriksa apakah productPrice kosong atau tidak ,kalau tidak aktif maka akan di skip
            Store store = productPrice.get().getStore(); // ini digunakan untuk jika harga product yang aktif ditemukan di store ,maka harga akan di masukan kedam store

            productResponses.add(toProductResponse(store,product,productPrice.get()));
        }
        return new PageImpl<>(productResponses,pageable,products.getTotalElements());
    }
}