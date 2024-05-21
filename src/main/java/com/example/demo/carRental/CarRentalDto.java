package com.example.demo.carRental;

import java.sql.Date;

import com.example.demo.car.CarDto;
import com.example.demo.carRental.CarRental.Status;
import com.example.demo.user.UserDto;


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
