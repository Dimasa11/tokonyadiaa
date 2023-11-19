package com.enigma.tokonyadiaa.service;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.entity.Order;
import com.enigma.tokonyadiaa.model.request.OrderRequest;
import com.enigma.tokonyadiaa.model.response.OrderResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    OrderResponse createNewTransaction(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(String id);
    OrderResponse updateOrder(OrderRequest orderRequest, String id);

    void deleteOrder(String id);

}
