package com.enigma.tokonyadiaa.controller;

import com.enigma.tokonyadiaa.entity.Store;
import com.enigma.tokonyadiaa.exception.ResourceNotFoundException;
import com.enigma.tokonyadiaa.model.response.StoreResponse;
import com.enigma.tokonyadiaa.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private final StoreService storeService;

    @PostMapping
    public Store create(@RequestBody Store store) {
        return storeService.create(store);
    }

    @GetMapping(path ="/{id}")
    public Store getById(@PathVariable String id) {
        return storeService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAll() {
        List<StoreResponse> storeResponses =storeService.getAll();

        return new ResponseEntity<>(storeResponses , HttpStatus.OK);
    }

    @PutMapping(path ="/{id}")
    public Store update(@PathVariable String id, @RequestBody Store store) {
        store.setId(id);
        return storeService.update(store);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        storeService.deleteById(id);
    }

}
