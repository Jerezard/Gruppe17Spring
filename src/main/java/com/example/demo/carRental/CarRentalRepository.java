package com.example.demo.carRental;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Integer> {
    Optional<CarRental> findByCar_CarIDAndUser_UserIDAndRentalStatus(int carId, int userId, CarRental.Status status);
}
