package com.enigma.tokonyadiaa.service;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.entity.Store;

import java.util.List;

public interface CustomerService {
    Customer create (Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    Customer update (Customer customer);
    void delete(String id);
}
