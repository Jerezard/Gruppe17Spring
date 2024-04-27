package com.example.demo;

import org.springframework.stereotype.Component;

import com.example.demo.car.Car;
import com.example.demo.car.CarRepository;
import com.example.demo.car.ExtraFeatures;
import com.example.demo.car.ExtraFeaturesRepo;
import com.example.demo.car.Price;
import com.example.demo.car.PriceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final CarRepository carRepo;
    private final ExtraFeaturesRepo extraFeaturesRepo;
    private final PriceRepository priceRepo;

    public SampleDataLoader(CarRepository carRepo, ExtraFeaturesRepo extraFeaturesRepo, PriceRepository priceRepo){
        this.carRepo = carRepo;
        this.extraFeaturesRepo = extraFeaturesRepo;
        this.priceRepo = priceRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        addCar("Nissan", "Leaf", "2016", "White", "Electric", "Automatic", 5, "ABC1234", true, "none",
            new ArrayList<>(Arrays.asList(new Price("Auto 9-9", 500), new Price("Auto 10-10", 500))));
        addCar("Mazda", "2", "2017", "White", "Petrol","Automatic",5,"XYZ5678",true, "Dab radio",
            new ArrayList<>(Arrays.asList(new Price("Biklist", 400))));
        addCar("Volkswagen", "Golf", "2007", "White", "Diesel", "Manual", 5, "XYZ1234", true, "Bluetooth, DAB radio, warming in the chairs",
            new ArrayList<>(Arrays.asList(new Price("Miller Bil", 600), new Price("Biller Bil", 550))));
        addCar("Tesla", "Model 3", "2019", "Black", "Electric", "Automatic", 5, "ABC5678", true, "autonomous driving, long range, warming in the seats",
            new ArrayList<>(Arrays.asList(new Price("Biggernes Tesla", 700), new Price("Tesla Tom (private)", 500))));
        addCar("Tesla", "Model Y", "2022", "Blue", "Electric", "Automatic", 5, "DEF9012", true, "Four wheel drive, glass roof, autonomous driving",
            new ArrayList<>(Arrays.asList(new Price("Biggernes Tesla", 900), new Price("Tesla Tom (private)", 700))));
        addCar("Volkswagen", "Transporter", "1978", "Yellow", "Petrol", "Manual", 8, "GHI3456", true, "Retro",
            new ArrayList<>(Arrays.asList(new Price("Ørsta kommune", 200), new Price("Sirkelsliper", 70), new Price("Peace per", 180))));
        addCar("BMW", "M3 Evo", "1988", "Black", "Petrol", "Manual", 4, "JKL7890", true, "Three stripes, original tire discs",
            new ArrayList<>(Arrays.asList(new Price("Bilverksted", 400), new Price("Grabes", 450), new Price("Djarney", 449))));
        addCar("Skoda", "Fabia", "2011", "Green", "Diesel", "Automatic", 5, "MNO1234", true, "Tow hook",
            new ArrayList<>(Arrays.asList(new Price("Sprekksaver", 300), new Price("Smidig bilforhandler", 299), new Price("Fossefall bilfornhandler", 700))));
        addCar("Peugeot", "307 SW", "2008", "Silver", "Diesel", "Manual", 7, "PQR5678", true, "Travel box on the roof",
            new ArrayList<>(Arrays.asList(new Price("Betrel Ostein", 600), new Price("Auto 10-10", 550))));
        addCar("Peugeot", "207", "2007", "Gray", "Diesel", "Manual", 5, "STU9012", true, "glass window, warming in the seats, warming in the steering wheel, warming in the mirrors, warming in the tires, warming under the rubber rugs, warming 360",
            new ArrayList<>(Arrays.asList(new Price("Betrel Ostein", 500), new Price("Auto 10-10", 550))));
        addCar("Peugeot", "3008", "2010", "Red", "Diesel", "Manual", 5, "VWX3456", true, "FM radio, CD player, Metallic paint",
            new ArrayList<>(Arrays.asList(new Price("Betrel Ostein", 600), new Price("Auto 10-10", 600))));
        addCar("Peugeot", "iOn", "2015", "White", "Electric", "Automatic", 4, "YZA7890", true, "five doors, very economic",
            new ArrayList<>(Arrays.asList(new Price("Betrel Ostein", 200), new Price("Auto 10-10", 201))));

          
    }

    private void addCar(String make, String model, String year, String color, String fuelType, 
    String transmissionType, int numSeats, String registrationNumber, boolean availabilityStatus, String extraFeature, List<Price> prices){

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

        carRepo.save(car);

        ExtraFeatures extraFeatures = new ExtraFeatures();
        extraFeatures.setExtraFeature(extraFeature);
        extraFeatures.setCar(car);
        extraFeaturesRepo.save(extraFeatures);

        for(Price price : prices){
            price.setCar(car);
            priceRepo.save(price);
        }
    }
    
}
