package com.enigma.tokonyadiaa.service.impl;


import com.enigma.tokonyadiaa.model.response.StoreResponse;
import com.enigma.tokonyadiaa.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.enigma.tokonyadiaa.entity.Store;
import com.enigma.tokonyadiaa.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    @Autowired
    private final StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getById(String id) {
        return storeRepository.findById(id).get();
    }

    @Override
    public List<StoreResponse> getAll() {
        List<Store> stores = storeRepository.findAll();
        List<StoreResponse> storeResponses  = new ArrayList<>();

        for(Store store : stores){
            StoreResponse response = StoreResponse.builder()
                    .id(store.getId())
                    .name(store.getName())
                    .address(store.getAddress())
                    .noSiup(store.getNoSiup())
                    .phone(store.getPhone())
                    .build();
            storeResponses.add(response);

        }
        return storeResponses;
    }

    @Override
    public Store update(Store store) {
        //Store currentStore = storeRepository.findById(store.getId()).orElse(null);
        Store currentStore = getById(store.getId());
        if (currentStore != null) {
            currentStore.setName(store.getName());
            currentStore.setAddress(store.getAddress());
            currentStore.setPhone(store.getPhone());
            return storeRepository.save(currentStore);
        }
        return null;



    }

    @Override
    public void deleteById(String storeId) {
        storeRepository.deleteById(storeId);
    }
}