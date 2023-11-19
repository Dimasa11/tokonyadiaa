package com.enigma.tokonyadiaa.repository;

import com.enigma.tokonyadiaa.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice ,String> {
//KONDISI KETIKA KITA HARUS PILIH DULU PRICE YANG AKTIF ATAU TIDAK
    Optional<ProductPrice> findByProduct_idAndIsActive(String productId, boolean active);
}
