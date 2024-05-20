package com.example.demo;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.car.Car;
import com.example.demo.car.CarRepository;
import com.example.demo.car.ExtraFeatures;
import com.example.demo.car.ExtraFeaturesRepo;
import com.example.demo.car.Price;
import com.example.demo.car.PriceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleDataLoader{

    @Bean
    CommandLineRunner loadData(CarRepository carRepo, ExtraFeaturesRepo extraFeaturesRepo, PriceRepository priceRepo) {
        return args -> {
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo, "Nissan", "Leaf", "2016", "White", "Electric", "Automatic", 5, "ABC1234", true, "none",
                Arrays.asList(new Price("Auto 9-9", 500), new Price("Auto 10-10", 500)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo, "Mazda", "2", "2017", "White", "Petrol", "Automatic", 5, "XYZ5678", true, "Dab radio",
                Arrays.asList(new Price("Biklist", 400)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Volkswagen", "Golf", "2007", "White", "Diesel", "Manual", 5, "XYZ1234", true, "Bluetooth, DAB radio, warming in the chairs",
                Arrays.asList(new Price("Miller Bil", 600), new Price("Biller Bil", 550)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Tesla", "Model 3", "2019", "Black", "Electric", "Automatic", 5, "ABC5678", true, "autonomous driving, long range, warming in the seats",
                Arrays.asList(new Price("Biggernes Tesla", 700), new Price("Tesla Tom (private)", 500)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Tesla", "Model Y", "2022", "Blue", "Electric", "Automatic", 5, "DEF9012", true, "Four wheel drive, glass roof, autonomous driving",
                Arrays.asList(new Price("Biggernes Tesla", 900), new Price("Tesla Tom (private)", 700)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Volkswagen", "Transporter", "1978", "Yellow", "Petrol", "Manual", 8, "GHI3456", true, "Retro",
                Arrays.asList(new Price("Ørsta kommune", 200), new Price("Sirkelsliper", 70), new Price("Peace per", 180)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"BMW", "M3 Evo", "1988", "Black", "Petrol", "Manual", 4, "JKL7890", true, "Three stripes, original tire discs",
               Arrays.asList(new Price("Bilverksted", 400), new Price("Grabes", 450), new Price("Djarney", 449)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Skoda", "Fabia", "2011", "Green", "Diesel", "Automatic", 5, "MNO1234", true, "Tow hook",
                Arrays.asList(new Price("Sprekksaver", 300), new Price("Smidig bilforhandler", 299), new Price("Fossefall bilfornhandler", 700)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Peugeot", "307 SW", "2008", "Silver", "Diesel", "Manual", 7, "PQR5678", true, "Travel box on the roof",
                Arrays.asList(new Price("Betrel Ostein", 600), new Price("Auto 10-10", 550)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Peugeot", "207", "2007", "Gray", "Diesel", "Manual", 5, "STU9012", true, "glass window, warming in the seats, warming in the steering wheel, warming in the mirrors, warming in the tires, warming under the rubber rugs, warming 360",
                Arrays.asList(new Price("Betrel Ostein", 500), new Price("Auto 10-10", 550)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Peugeot", "3008", "2010", "Red", "Diesel", "Manual", 5, "VWX3456", true, "FM radio, CD player, Metallic paint",
                Arrays.asList(new Price("Betrel Ostein", 600), new Price("Auto 10-10", 600)));
            addCarIfNotExists(carRepo, extraFeaturesRepo, priceRepo,"Peugeot", "iOn", "2015", "White", "Electric", "Automatic", 4, "YZA7890", true, "five doors, very economic",
                Arrays.asList(new Price("Betrel Ostein", 200), new Price("Auto 10-10", 201)));
        };
    }

    @Transactional
    void addCarIfNotExists(CarRepository carRepo, ExtraFeaturesRepo extraFeaturesRepo, PriceRepository priceRepo, String make, String model, String year, String color, String fuelType, 
    String transmissionType, int numSeats, String registrationNumber, boolean availabilityStatus, String extraFeature, List<Price> prices){

        Optional<Car> existingCar = carRepo.findByRegistrationNumber(registrationNumber);
        if (existingCar.isPresent()){
            return;
        }

        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setColor(color);
        car.setFuelType(fuelType);
        car.setTransmissionType(transmissionType);
        car.setNumSeats(numSeats);
        car.setRegistrationNumber(registrationNumber);
        car.setAvailabilityStatus(availabilityStatus);

        for(Price price : prices){
            price.setCar(car);
        }
        car.setPrices(prices);

        ExtraFeatures extraFeatures = new ExtraFeatures();
        extraFeatures.setExtraFeature(extraFeature);
        extraFeatures.setCar(car);
        car.setExtraFeatures(extraFeatures);

        carRepo.save(car);
    }
    
}
