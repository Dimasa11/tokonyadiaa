package com.enigma.tokonyadiaa.controller;

import com.enigma.tokonyadiaa.entity.Product;
import com.enigma.tokonyadiaa.model.request.ProductRequest;
import com.enigma.tokonyadiaa.model.response.CommonResponse;
import com.enigma.tokonyadiaa.model.response.PagingResponse;
import com.enigma.tokonyadiaa.model.response.ProductResponse;
import com.enigma.tokonyadiaa.model.response.StoreResponse;
import com.enigma.tokonyadiaa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


   @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

   @GetMapping("/alls")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path ="/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }


    @PutMapping(path ="/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        return productService.updateProduct(product);
    }

    @DeleteMapping(path ="/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }


    @PostMapping("/all")
    public ResponseEntity<?> createProductAll(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProductt(productRequest);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Succesfully Create New Product")
                .data(productResponse)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<ProductResponse>>> getAllProduct() {
        List<ProductResponse> productResponses = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<ProductResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Succesfully get All Product")
                        .data(productResponses)
                        .build());

    }

    @GetMapping("/allByName")
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "maxPrice",required = false) Long maxPrice,
            @RequestParam(name = "page",required = false , defaultValue = "0") Integer page,
            @RequestParam(name = "size",required = false, defaultValue = "5") Integer size
    ) {
        Page<ProductResponse> productResponses = productService.getAllByNameOrPrice(name,maxPrice,page,size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(productResponses.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all customer")
                        .data(productResponses.getContent())
                        .paging(pagingResponse)
                        .build());
    }





}
