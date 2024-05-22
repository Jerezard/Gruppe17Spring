package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
public class ExtraFeatures {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_feature_id")
    private int extraFeatureID;
    @Column(name = "extra_feature")
    private String extraFeature;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public ExtraFeatures(){

    }
    
    public ExtraFeatures(int extraFeatureID, String extraFeature) {

        this.extraFeatureID = extraFeatureID;
        this.extraFeature = extraFeature;

    }

    public int getExtraFeatureID() {
        return extraFeatureID;
    }

    public void setExtraFeatureID(int extraFeatureID) {
        this.extraFeatureID = extraFeatureID;
    }

    public String getExtraFeature() {
        return extraFeature;
    }

    public void setExtraFeature(String extraFeature) {
        this.extraFeature = extraFeature;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
