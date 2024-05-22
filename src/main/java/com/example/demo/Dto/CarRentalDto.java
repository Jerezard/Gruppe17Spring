package com.example.demo.Dto;

import java.sql.Date;

import com.example.demo.Entity.CarRental.Status;


public class CarRentalDto {
    

    private Date rentalStartDate;
    private Date rentalEndDate;
    private Status rentalStatus;
    private CarDto car;
    private UserDto user;



    public Date getRentalStartDate() {
        return rentalStartDate;
    }
    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }
    public Date getRentalEndDate() {
        return rentalEndDate;
    }
    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }
    public Status getRentalStatus() {
        return rentalStatus;
    }
    public void setRentalStatus(Status rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public CarDto getCar() {
        return car;
    }
    public void setCar(CarDto car) {
        this.car = car;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }

    

    

    

}
