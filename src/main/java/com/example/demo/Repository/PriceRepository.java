package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Price;

public interface PriceRepository extends JpaRepository <Price, Integer> {
    
}
