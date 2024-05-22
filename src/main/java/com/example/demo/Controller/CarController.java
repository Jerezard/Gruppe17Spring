package com.example.demo.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.CarDto;
import com.example.demo.Dto.PriceDto;
import com.example.demo.Service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable int id){
        Optional<CarDto> cars = carService.getCarById(id);
        return cars.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/availability/{status}")
    public ResponseEntity<List<CarDto>> getCarsByAvailabilityStatus(@PathVariable boolean status){
        List<CarDto> cars = carService.getCarByStatus(status);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}/prices")
    public ResponseEntity<List<PriceDto>> getPricesByCarId(@PathVariable int id){
        List<PriceDto> prices = carService.getPricesByCarId(id);
        if(prices!=null){
            return ResponseEntity.ok(prices);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/total-price")
    public ResponseEntity<Double> getTotalRentalPrice(@PathVariable int id, @RequestParam int days){
        Optional<Double> totalPrice = carService.calculateTotalRentalPrice(id, days);
        return totalPrice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sortLowPrice")
    public ResponseEntity<List<CarDto>> getAllCarsSortedByLowestPrice(){
        List<CarDto> cars = carService.getAllCarsSortByLowestPrice();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/sortHighPrice")
    public ResponseEntity<List<CarDto>> getAllCarsSortedByHighestPrice(){
        List<CarDto> cars = carService.getAllCarsSortByHighestPrice();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarDto>> searchCars(
        @RequestParam(required = false) String make,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) String year,
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice,
        @RequestParam(required = false) Boolean availabilityStatus
    ){
        List<CarDto> cars = carService.searchCars(make,model,year,minPrice,maxPrice,availabilityStatus);
        return ResponseEntity.ok(cars);
    }


}
