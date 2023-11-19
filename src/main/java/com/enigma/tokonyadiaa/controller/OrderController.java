package com.enigma.tokonyadiaa.controller;

import com.enigma.tokonyadiaa.entity.Order;
import com.enigma.tokonyadiaa.model.request.OrderRequest;
import com.enigma.tokonyadiaa.model.response.CommonResponse;
import com.enigma.tokonyadiaa.model.response.OrderResponse;
import com.enigma.tokonyadiaa.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createNewTransaction(@RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.createNewTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new transaction")
                        .data(orderResponse)
                        .build());
    }





}
