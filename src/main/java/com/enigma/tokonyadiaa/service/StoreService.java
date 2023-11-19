package com.enigma.tokonyadiaa.service;

import com.enigma.tokonyadiaa.entity.Store;
import com.enigma.tokonyadiaa.exception.ResourceNotFoundException;
import com.enigma.tokonyadiaa.model.response.StoreResponse;

import java.util.List;

public interface StoreService {
    Store create ( Store store);
    Store getById(String id);
    List<StoreResponse> getAll();
    Store update(Store store);
    void deleteById(String storeId);
}
