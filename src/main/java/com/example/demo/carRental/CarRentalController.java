package com.example.demo.carRental;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-rentals")
public class CarRentalController {
    
    @Autowired
    private CarRentalService carRentalService;

    @PostMapping
    public ResponseEntity<?> createCarRental(@RequestBody CarRentalDto carRentalDto){
        if(carRentalDto.getCar() == null || carRentalDto.getUser() == null ){
            return ResponseEntity.badRequest().body("Car and User must be provided");
        }
        
        try{
            CarRental carRental = carRentalService.createCarRental(carRentalDto);
            return ResponseEntity.ok(carRental);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<CarRental> updateCarRentalStatus(@PathVariable int id,@RequestParam String status){
        CarRental carRental = carRentalService.updateCarRentalStatus(id, status);
        return ResponseEntity.ok(carRental);
    }

    @GetMapping
    public ResponseEntity<List<CarRentalDto>> getAllRentals(){
        List<CarRentalDto> carRental = carRentalService.getAllRentals();
        return ResponseEntity.ok(carRental);
    }
}
