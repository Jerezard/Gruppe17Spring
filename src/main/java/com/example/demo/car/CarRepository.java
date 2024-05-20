package com.example.demo.car;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByRegistrationNumber(String registrationNumber);
    List<Car> findByAvailabilityStatus(boolean availabilityStatus);
}