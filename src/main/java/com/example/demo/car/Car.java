package com.example.demo.car;

import jakarta.persistence.*;

@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carID;

    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    private String year;
    private String color;
    @Column(name = "fuel_Type")
    private String fuelType;
    @Column(name = "transmission_Type")
    private String transmissionType;
    @Column(name = "num_Seats")
    private int numSeats;
    @Column(name = "registration_Number")
    private String registrationNumber;
    @Column(name = "availability_Status")
    private boolean availabilityStatus;

    // @OneToMany
    // @JoinTable(
    //     name = "car_extra_features",
    //     joinColumns = @JoinColumn(name = "car_id"),
    //     inverseJoinColumns = @JoinColumn(name = "extra_feature_id")
    // )
    // private Set<ExtraFeatures> extraFeatures;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private ExtraFeatures extraFeatures;

    public Car(){

    }

    private Car(int carID, String make, String model, String year, String color, String registrationNumber,
     boolean availabilityStatus, String fuelType, String transmissionType, int numSeats){

        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.availabilityStatus = availabilityStatus;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.numSeats = numSeats;


    }

    public int getCarID() {
        return carID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public String getFuelType(){
        return fuelType;
    }

    public String getTransmissionType(){
        return transmissionType;
    }

    public int getNumSeats(){
        return numSeats;
    }

    // Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFuelType(String fuelType){
        this.fuelType = fuelType;
    }

    public void setTransmissionType(String transmissionType){
        this.transmissionType = transmissionType;
    }

    public void setNumSeats(int numSeats){
        this.numSeats = numSeats;
    }


    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}

