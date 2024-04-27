package com.example.demo.carRental;

import jakarta.persistence.*;
import java.sql.Date;

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
    


    private int carID;
    private int userID;
    
}
