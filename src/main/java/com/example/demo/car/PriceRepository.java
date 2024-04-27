package com.example.demo.car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository <Price, Integer> {
    
}
