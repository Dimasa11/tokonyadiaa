package com.enigma.tokonyadiaa.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterSellerRequest {
    private String email;
    private String password;
    private String userName;
    private String storeName;
    private String mobilePhone;
}
