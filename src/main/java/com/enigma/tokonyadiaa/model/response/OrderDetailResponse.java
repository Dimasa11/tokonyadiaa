package com.enigma.tokonyadiaa.model.response;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailResponse {
    private String orderDetailId;
    private ProductResponse product;
    private Integer quantity;
}
