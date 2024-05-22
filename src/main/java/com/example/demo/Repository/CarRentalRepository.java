package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.CarRental;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Integer> {
    Optional<CarRental> findByCar_CarIDAndUser_UserIDAndRentalStatus(int carId, int userId, CarRental.Status status);
    List<CarRental> findByCarCarID(int carId);
}
