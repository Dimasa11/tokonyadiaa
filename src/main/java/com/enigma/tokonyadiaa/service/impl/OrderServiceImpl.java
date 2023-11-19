package com.enigma.tokonyadiaa.service.impl;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.entity.Order;
import com.enigma.tokonyadiaa.entity.OrderDetail;
import com.enigma.tokonyadiaa.entity.ProductPrice;
import com.enigma.tokonyadiaa.model.request.OrderRequest;
import com.enigma.tokonyadiaa.model.response.*;
import com.enigma.tokonyadiaa.repository.OrderDetailRepository;
import com.enigma.tokonyadiaa.repository.OrderRepository;
import com.enigma.tokonyadiaa.service.CustomerService;
import com.enigma.tokonyadiaa.service.OrderService;
import com.enigma.tokonyadiaa.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;


    @Override
    public List<OrderResponse> getAllOrders() {
        return null;
    }

    @Override
    public OrderResponse getOrderById(String id) {
        return null;
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, String id) {
        return null;
    }

    @Override
    public void deleteOrder(String id) {

    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse createNewTransaction(OrderRequest orderRequest) {
        // TODO 1 : Validate customer
        Customer customer = customerService.getById(orderRequest.getCustomerId());
        // TODO 2 : Convert orderDetailRequest to orderDetail
        List<OrderDetail> orderDetails = orderRequest.getOrderDetail().stream().map(orderDetailRequest -> {
            //TODO 3 : Validate Product Price
            ProductPrice productPrice = productPriceService.getById(orderDetailRequest.getProductPriceId());
            return OrderDetail.builder()
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getQuantity())
                    .build();
        }).collect(Collectors.toList());
        // TODO 4 : Crete New Order
        Order order = Order.builder()
                .customer(customer)
                .transDate(LocalDateTime.now())
                .orderDetails(orderDetails)
                .build();
        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(orderDetail -> {
            //TODO 5 : Set order from orderDetail after creating new order
            orderDetail.setOrder(order);
            System.out.println(order);
            // TODO 6 : Change the stock from the purchase quantity
            ProductPrice currentProductPrice = orderDetail.getProductPrice();
            currentProductPrice.setStock(currentProductPrice.getStock() - orderDetail.getQuantity());
            return OrderDetailResponse.builder()
                    .orderDetailId(orderDetail.getId())
                    .quantity(orderDetail.getQuantity())
                    //TODO 7 : Convert product to productResponse(from productPrice)
                    .product(ProductResponse.builder()
                            .id(currentProductPrice.getProduct().getId())
                            .productName(currentProductPrice.getProduct().getName())
                            .description(currentProductPrice.getProduct().getDescription())
                            .price(currentProductPrice.getPrice())
                            .stock(currentProductPrice.getStock())
                            //TODO 8 : Convert store to storeResponse (from productPrice)
                            .store(StoreResponse.builder()
                                    .id(currentProductPrice.getStore().getId())
                                    .name(currentProductPrice.getStore().getName())
                                    .address(currentProductPrice.getStore().getAddress())
                                    .build())
                            .build())
                    .build();
        }).collect(Collectors.toList());

        //TODO 9 : Convert customer to customerResponse\
        CustomerResponse customerResponse = CustomerResponse.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .build();

        return OrderResponse.builder()
                .orderId(order.getId())
                .customer(customerResponse)
                .transDate(order.getTransDate())
                .orderDetail(orderDetailResponses)
                .build();
    }

}

