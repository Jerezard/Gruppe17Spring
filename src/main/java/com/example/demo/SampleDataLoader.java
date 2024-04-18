package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final CarRepository carRepo;
    private final ExtraFeaturesRepo extraFeaturesRepo;

    public SampleDataLoader(CarRepository carRepo, ExtraFeaturesRepo extraFeaturesRepo){
        this.carRepo = carRepo;
        this.extraFeaturesRepo = extraFeaturesRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        addCar("Nissan", "Leaf", "2016", "White", "Electric", "Automatic", 5, "ABC1234", true, "none");
        addCar("Mazda", "2", "2017", "White", "Petrol","Automatic",5,"XYZ5678",true, "Dab radio");
        addCar("Volkswagen", "Golf", "2007", "White", "Diesel", "Manual", 5, "XYZ1234", true, "Bluetooth, DAB radio, warming in the chairs");
        addCar("Tesla", "Model 3", "2019", "Black", "Electric", "Automatic", 5, "ABC5678", true, "autonomous driving, long range, warming in the seats");
        addCar("Tesla", "Model Y", "2022", "Blue", "Electric", "Automatic", 5, "DEF9012", true, "Four wheel drive, glass roof, autonomous driving");
        addCar("Volkswagen", "Transporter", "1978", "Yellow", "Petrol", "Manual", 8, "GHI3456", true, "Retro");
        addCar("BMW", "M3 Evo", "1988", "Black", "Petrol", "Manual", 4, "JKL7890", true, "Three stripes, original tire discs");
        addCar("Skoda", "Fabia", "2011", "Green", "Diesel", "Automatic", 5, "MNO1234", true, "Tow hook");
        addCar("Peugeot", "307 SW", "2008", "Silver", "Diesel", "Manual", 7, "PQR5678", true, "Travel box on the roof");
        addCar("Peugeot", "207", "2007", "Gray", "Diesel", "Manual", 5, "STU9012", true, "glass window, warming in the seats, warming in the steering wheel, warming in the mirrors, warming in the tires, warming under the rubber rugs, warming 360");
        addCar("Peugeot", "3008", "2010", "Red", "Diesel", "Manual", 5, "VWX3456", true, "FM radio, CD player, Metallic paint");
        addCar("Peugeot", "iOn", "2015", "White", "Electric", "Automatic", 4, "YZA7890", true, "five doors, very economic");

          
    }

    private void addCar(String make, String model, String year, String color, String fuelType, 
    String transmissionType, int numSeats, String registrationNumber, boolean availabilityStatus, String extraFeature){

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
    }
    
}
