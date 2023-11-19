package com.enigma.tokonyadiaa.controller;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void isShouldCreateCustomerAndReturnCustomerResponseAndStatusCode() throws Exception {
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("324");
        dummyCustomer.setName("dimas");

        //mock behavior
        when(customerService.create(dummyCustomer)).thenReturn(dummyCustomer);

        //verify untuk mengirikan permintaan HTTP ke endpoint controller
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dummyCustomer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message.").value("Succesfully create new customer"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()));


        verify(customerService,times(1)).create(any(Customer.class));
    }

    @Test
    void getCustomerById() throws Exception {
        // Dummy customer data
        String customerId = "123";
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(customerId);
        dummyCustomer.setName("John");

        // Mock behavior to return the customer
        when(customerService.getById(customerId)).thenReturn(dummyCustomer);

        // Perform the HTTP request to get a customer by ID
        mockMvc.perform(get("/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully retrieved customer by ID"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()));

        // Verify that the 'getById' method of 'customerService' was called once with the correct ID
        verify(customerService, times(1)).getById(customerId);
    }

    @Test
    void getAllCustomers() throws Exception {
        // Dummy customer data
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("1");
        dummyCustomer.setName("dimas");

        Customer dummyCustomer2 = new Customer();
        dummyCustomer.setId("2");
        dummyCustomer.setName("aji");



        List<Customer> dummyCustomers = Arrays.asList(dummyCustomer,dummyCustomer2);

        // Mock behavior to return the list of customers
        when(customerService.getAll()).thenReturn(dummyCustomers);

        // Perform the HTTP request to get all customers
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully retrieved all customers"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer2.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer2.getName()));

        // Verify that the 'getAll' method of 'customerService' was called once
        verify(customerService, times(1)).getAll();
    }

    @Test
    void shouldUpdateCustomerAndReturnUpdatedCustomer() throws Exception {
        // Dummy customer data
        String customerId = "123";
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(customerId);
        dummyCustomer.setName("DIMAS");

        // Prepare an updated customer
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName("Updated DIMAS");

        // Mock behavior to return the updated customer
        when(customerService.update(updatedCustomer)).thenReturn(updatedCustomer);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCustomer)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Successfully Updated Customer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(updatedCustomer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(updatedCustomer.getName()));

        // Verify that the 'update' method of 'customerService' was called once with the updated customer
        verify(customerService, times(1)).update(updatedCustomer);
    }

    @Test
    void testDeleteCustomer() throws Exception {
        String customerId = "123"; // ID pelanggan yang akan dihapus

        // Mock pemanggilan service.delete
        doNothing().when(customerService).delete(customerId);

        // Lakukan pengujian penghapusan pelanggan
        mockMvc.perform(delete("/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully delete customer with id " + customerId))
                .andExpect(jsonPath("$.data").value("OK"));

        // Verifikasi bahwa metode service.delete dipanggil dengan benar
        verify(customerService, times(1)).delete(customerId);
    }
}