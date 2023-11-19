package com.enigma.tokonyadiaa.service.impl;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.repository.CustomerRepository;
import com.enigma.tokonyadiaa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        Customer customer1 = getById(customer.getId());
        if(customer1 != null){
            customer1.setName(customer.getName());
            customer1.setAddress(customer.getAddress());
            customer1.setPhone(customer.getPhone());
            customer1.setEmail(customer.getEmail());
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
