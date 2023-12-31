package com.enigma.tokonyadiaa.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {
    private String emails;
    private String password;
    private String name;
    private String address;
    private String mobilePhone;

}
