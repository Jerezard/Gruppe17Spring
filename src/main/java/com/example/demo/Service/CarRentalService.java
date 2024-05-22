package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.CarRentalDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.Car;
import com.example.demo.Entity.CarRental;
import com.example.demo.Entity.User;
import com.example.demo.Exception.NullValueExpeption;
import com.example.demo.Repository.CarRentalRepository;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class CarRentalService {
    
    @Autowired
    private CarRentalRepository carRentalRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    public CarRental createCarRental(CarRentalDto carRentalDto){

        Car car = carRepository.findById(carRentalDto.getCar().getCarID())
            .orElseThrow(IllegalArgumentException::new);
        
        if(!car.getAvailabilityStatus()){
            throw new IllegalStateException("Car is not available for rental");
        }

        User user = userRepository.findById(carRentalDto.getUser().getUserID())
            .orElseThrow(IllegalArgumentException::new);

        if(carRentalDto.getCar() == null || carRentalDto.getUser() == null ){
            throw new NullValueExpeption("Null values in car or user");
        }

        CarRental carRental = convertToEntity(carRentalDto);
        return carRentalRepository.save(carRental);

        
    }


    public List<CarRentalDto> updateCarRentalStatus(int carId, CarRental.Status status) {
        List<CarRental> carRentals = carRentalRepository.findByCarCarID(carId);

        List<CarRentalDto> updatedCarRentals = new ArrayList<>();

        for (CarRental carRental : carRentals) {
        carRental.setRentalStatus(status);
        if (status == CarRental.Status.ONGOING) {
            carRental.getCar().setAvailabilityStatus(false);
        } else {
            carRental.getCar().setAvailabilityStatus(true);
        }
        CarRental updatedCarRental = carRentalRepository.save(carRental);
        updatedCarRentals.add(convertCarRentalToDto(updatedCarRental));
    }

        return updatedCarRentals;
    }

    public List<CarRentalDto> getAllRentals(){
        List<CarRental> carRentals = carRentalRepository.findAll();
        return carRentals.stream().map(this::convertCarRentalToDto).collect(Collectors.toList());
    }


    private CarRentalDto convertCarRentalToDto(CarRental carRental) {

        CarRentalDto carRentalDto = new CarRentalDto();
        carRentalDto.setRentalStartDate(carRental.getRentalStartDate());
        carRentalDto.setRentalEndDate(carRental.getRentalEndDate());
        carRentalDto.setRentalStatus(carRental.getRentalStatus());
    
        if (carRental.getCar() != null) {
            carRentalDto.setCar(carService.convertToDto(carRental.getCar()));
        }
    
        if (carRental.getUser() != null) {
            carRentalDto.setUser(convertToUserDto(carRental.getUser()));
        }
    
        return carRentalDto;
    }

    private UserDto convertToUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAdmin(user.isAdmin());
        return userDto;
    }

    private CarRental convertToEntity(CarRentalDto carRentalDto){

        
        CarRental carRental = new CarRental();
        carRental.setRentalStartDate(carRentalDto.getRentalStartDate());
        carRental.setRentalEndDate(carRentalDto.getRentalEndDate());
        carRental.setRentalStatus(carRentalDto.getRentalStatus());

        Car car = carRepository.findById(carRentalDto.getCar().getCarID()).orElseThrow(() -> new IllegalArgumentException("Invalid car ID"));
        User user = userRepository.findById(carRentalDto.getUser().getUserID()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        
        carRental.setCar(car);
        carRental.setUser(user);

        return carRental;

    }
}
