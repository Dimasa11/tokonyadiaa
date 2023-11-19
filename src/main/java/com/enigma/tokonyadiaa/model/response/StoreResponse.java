package com.enigma.tokonyadiaa.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreResponse {
    private String id;
    private String name;
    private  String address;
    private String noSiup;
    private String phone;
}
