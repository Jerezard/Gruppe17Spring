package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car>{
    Optional<Car> findByRegistrationNumber(String registrationNumber);
    List<Car> findByAvailabilityStatus(boolean availabilityStatus);
    
    @Query("SELECT c FROM Car c JOIN c.prices p GROUP BY c ORDER BY MIN(p.amount) ASC")
    List<Car> findAllCarsOrderedByLowestPrice();

    @Query("SELECT c FROM Car c JOIN c.prices p GROUP BY c ORDER BY MAX(p.amount) DESC")
    List<Car> findAllCarsOrderedByHighestPrice();
}