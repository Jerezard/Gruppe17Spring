package com.example.demo.carRental;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.car.Car;
import com.example.demo.car.CarDto;
import com.example.demo.car.CarRepository;
import com.example.demo.car.CarService;
import com.example.demo.car.PriceDto;
import com.example.demo.user.User;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserRepository;

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

    public CarRental createCarRental(CarRentalDto CarRentalDto){
        CarRental carRental = new CarRental();
        carRental.setRentalStartDate(CarRentalDto.getRentalStartDate());
        carRental.setRentalEndDate(CarRentalDto.getRentalEndDate());
        carRental.setRentalStatus(CarRentalDto.getRentalStatus());

        Optional<Car> car = carRepository.findById(CarRentalDto.getCarId());
        Optional<User> user = userRepository.findById(CarRentalDto.getUserId());

        if((car.isPresent() && user.isPresent())){
            carRental.setCar(car.get());
            carRental.setUser(user.get());
        } else {
            throw new IllegalArgumentException("Invalid car or user ID");
        }

        return carRentalRepository.save(carRental);

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
}
