package com.example.demo.carRental;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.DuplicateCarRentalException;
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

    public CarRental createCarRental(CarRentalDto carRentalDto){
        Optional<CarRental> existingRental = carRentalRepository.findByCar_CarIDAndUser_UserIDAndRentalStatus(
            carRentalDto.getCar().getCarID(),
            carRentalDto.getUser().getUserID(),
            CarRental.Status.ONGOING
            );

            if(existingRental.isPresent()){
                throw new DuplicateCarRentalException("That Car rental already exists");
            }

            CarRental carRental = convertToEntity(carRentalDto);
            return carRentalRepository.save(carRental);

    }


    public CarRental updateCarRentalStatus(int rentalId, String status){
        CarRental carRental = carRentalRepository.findById(rentalId).orElseThrow(() 
        -> new IllegalArgumentException("Invalid CarRental ID"));
        
        CarRental.Status newStatus = CarRental.Status.valueOf(status);
        carRental.setRentalStatus(newStatus);

        Car car = carRental.getCar();
        if(newStatus == CarRental.Status.COMPLETED || newStatus == CarRental.Status.CANCELED){
            car.setAvailabilityStatus(true);
        } else if (newStatus == CarRental.Status.ONGOING){
            car.setAvailabilityStatus(false);
        }
        carRepository.save(car);

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
