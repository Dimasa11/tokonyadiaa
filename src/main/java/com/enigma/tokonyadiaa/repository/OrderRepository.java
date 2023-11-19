package com.enigma.tokonyadiaa.repository;

import com.enigma.tokonyadiaa.entity.Customer;
import com.enigma.tokonyadiaa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
