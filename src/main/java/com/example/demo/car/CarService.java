package com.example.demo.car;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    public List<CarDto> getAllCars(){
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
    public Optional<CarDto> getCarById(int carId){
        Optional<Car> car = carRepository.findById(carId);
        return car.map(this::convertToDto);
    }

    public List<CarDto> getCarByStatus(boolean status){
        List<Car> cars = carRepository.findByAvailabilityStatus(status);
        return  cars.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<PriceDto> getPricesByCarId(int carId){
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent()){
            return car.get().getPrices().stream().map(this::convertToPriceDto).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public Optional<Double> calculateTotalRentalPrice(int carId, int days){
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            List<Price> prices = car.get().getPrices();
            if(!prices.isEmpty()){
                double pricePerDay = prices.get(0).getAmount();
                return Optional.of(pricePerDay * days);
            }
        }
        return Optional.empty();
    }

    public List<CarDto> getAllCarsSortByLowestPrice(){
        List<Car> cars = carRepository.findAllCarsOrderedByLowestPrice();
        return cars.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CarDto> getAllCarsSortByHighestPrice(){
        List<Car> cars = carRepository.findAllCarsOrderedByHighestPrice();
        return cars.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CarDto convertToDto(Car car){

        CarDto carDto = new CarDto();
        carDto.setCarID(car.getCarID());
        carDto.setMake(car.getMake());
        carDto.setModel(car.getModel());
        carDto.setYear(car.getYear());
        carDto.setColor(car.getColor());
        carDto.setFuelType(car.getFuelType());
        carDto.setTransmissionType(car.getTransmissionType());
        carDto.setNumSeats(car.getNumSeats());
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setAvailabilityStatus(car.getAvailabilityStatus());

        if(car.getPrices() != null){
            List<PriceDto> priceDto = car.getPrices().stream().map(this::convertToPriceDto).collect(Collectors.toList());
            carDto.setPrices(priceDto);
      }

      return carDto;

    }

    private PriceDto convertToPriceDto(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPriceID(price.getPriceID());
        priceDto.setDescription(price.getDescription());
        priceDto.setAmount(price.getAmount());
        return priceDto;
    }


}
