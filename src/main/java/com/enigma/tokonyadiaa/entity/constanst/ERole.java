package com.enigma.tokonyadiaa.entity.constanst;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum ERole {
    ROLE_CUSTOMER,
    ROLE_SELLER,
    ROLE_ADMIN;

    public static  ERole get(String value){
        for(ERole eRole: ERole.values()){
            if(eRole.name().equalsIgnoreCase(value))
                return eRole;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"ROLE NOT FOUND");
    }
}
