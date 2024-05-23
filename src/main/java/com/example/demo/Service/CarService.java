package com.example.demo.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.CarDto;
// import com.example.demo.Dto.ExtraFeaturesDto;
import com.example.demo.Dto.PriceDto;
import com.example.demo.Entity.Car;
// import com.example.demo.Entity.ExtraFeatures;
import com.example.demo.Entity.Price;
import com.example.demo.Repository.CarRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

    public List<CarDto> searchCars(String make, String model, String year, BigDecimal minPrice, BigDecimal maxPrice, Boolean availabilityStatus){
        List<Car> cars = carRepository.findAll(new Specification<Car>(){
            @Override
            public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicates = new ArrayList<>();
                if(make != null && !make.isEmpty()){
                    predicates.add(criteriaBuilder.equal(root.get("make"), make));
                }
                if(model != null && !model.isEmpty()){
                    predicates.add(criteriaBuilder.equal(root.get("model"), model));
                }
                if(year != null){
                    predicates.add(criteriaBuilder.equal(root.get("year"), year));
                }
                if (minPrice != null) {
                    Join<Car, Price> prices = root.join("prices");
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(prices.get("amount"), minPrice));
                }
                if (maxPrice != null) {
                    Join<Car, Price> prices = root.join("prices");
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(prices.get("amount"), maxPrice));
                }
                if (availabilityStatus != null) {
                    predicates.add(criteriaBuilder.equal(root.get("availabilityStatus"), availabilityStatus));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        });

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

        // if(car.getExtraFeatures() != null){
        //     ExtraFeaturesDto extraFeaturesDto = convertToExtraFeaturesDto(car.getExtraFeatures());
        //     carDto.setExtraFeatures(extraFeaturesDto);
        // }
            

      return carDto;

    }

    private PriceDto convertToPriceDto(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPriceID(price.getPriceID());
        priceDto.setDescription(price.getDescription());
        priceDto.setAmount(price.getAmount());
        return priceDto;
    }

    // private ExtraFeaturesDto convertToExtraFeaturesDto(ExtraFeatures extraFeatures){
    //     ExtraFeaturesDto extraFeaturesDto = new ExtraFeaturesDto();
    //     extraFeaturesDto.setExtraFeature(extraFeatures.getExtraFeature());
    //     extraFeaturesDto.setExtraFeatureID(extraFeatures.getExtraFeatureID());
    //     return extraFeaturesDto;
    // }


}
