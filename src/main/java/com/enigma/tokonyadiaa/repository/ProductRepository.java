package com.enigma.tokonyadiaa.repository;

import com.enigma.tokonyadiaa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> , JpaSpecificationExecutor<Product> {
}