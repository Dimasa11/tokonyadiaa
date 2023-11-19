package com.enigma.tokonyadiaa.repository;

import com.enigma.tokonyadiaa.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store , String> {
}
