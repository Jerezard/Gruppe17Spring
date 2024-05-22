package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
public class Price {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceID;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    //version, location or renter of car
    private String description;
    private double amount;

    public Price(){

    }
    public Price(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }


    public int getPriceID() {
        return priceID;
    }
    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
