package com.example.demo.carRental;

import jakarta.persistence.*;
import java.sql.Date;

import com.example.demo.car.Car;
import com.example.demo.user.User;

@Entity
public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalID;

    private Date rentalStartDate;
    private Date rentalEndDate;

        public enum Status{
            ONGOING, COMPLETED, CANCELED;
        }
    @Enumerated(EnumType.STRING) 
    private Status rentalStatus;
    

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
   
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    
    public CarRental(){

    }

    public CarRental(Date rentalStarDate, Date rentalEnDate, Status rentalStatus){

        this.rentalStartDate = rentalStarDate;
        this.rentalEndDate = rentalEndDate;
        this.rentalStatus = rentalStatus;

    }

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
