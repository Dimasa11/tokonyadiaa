package com.enigma.tokonyadiaa.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailRequest {
    private String productPriceId;
    private Integer quantity;

}
