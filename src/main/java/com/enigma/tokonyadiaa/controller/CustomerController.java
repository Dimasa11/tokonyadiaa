package com.enigma.tokonyadiaa.controller;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.model.request.ProductRequest;
import com.enigma.tokonyadiaa.model.response.CommonResponse;
import com.enigma.tokonyadiaa.model.response.ProductResponse;
import com.enigma.tokonyadiaa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<Customer>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Customer")
                        .data(customerService.create(customer))
                        .paging(null)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.<Customer>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Successfully get customer by id")
                            .data(customer)
                            .paging(null)
                            .build());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<Customer>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get All Customers")
                        .data(customers)
                        .paging(null)
                        .build());

    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.update(customer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<Customer>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Update Customer")
                        .data(updatedCustomer)
                        .paging(null)
                        .build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
        Customer customer = new Customer();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete customer")
                        .data(String.valueOf(customer))
                        .build());
    }

}