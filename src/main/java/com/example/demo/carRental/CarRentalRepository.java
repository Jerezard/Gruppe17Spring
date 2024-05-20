package com.example.demo.carRental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Integer> {
    
}
