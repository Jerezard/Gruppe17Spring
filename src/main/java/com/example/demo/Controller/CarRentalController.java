package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.CarRentalDto;
import com.example.demo.Entity.CarRental;
import com.example.demo.Service.CarRentalService;

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
    
    @PutMapping("/{id}/status")
    public ResponseEntity<CarRentalDto> updateCarRentalStatus(@PathVariable int id,@RequestParam CarRental.Status status){
        List<CarRentalDto> updatedCarRental = carRentalService.updateCarRentalStatus(id, status);
        
        if(!updatedCarRental.isEmpty()){
            return ResponseEntity.ok(updatedCarRental.get(0));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarRentalDto>> getAllRentals(){
        List<CarRentalDto> carRental = carRentalService.getAllRentals();
        return ResponseEntity.ok(carRental);
    }
}
