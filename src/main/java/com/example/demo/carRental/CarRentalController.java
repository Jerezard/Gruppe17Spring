package com.example.demo.carRental;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-rentals")
public class CarRentalController {
    
    @Autowired
    private CarRentalService carRentalService;

    @PostMapping
    public ResponseEntity<CarRental> createCarRental(@RequestBody CarRentalDto carRentalDto){
        CarRental carRental = carRentalService.createCarRental(carRentalDto);
        return ResponseEntity.ok(carRental);
    }

    @GetMapping
    public ResponseEntity<List<CarRentalDto>> getAllRentals(){
        List<CarRentalDto> carRental = carRentalService.getAllRentals();
        return ResponseEntity.ok(carRental);
    }
}
