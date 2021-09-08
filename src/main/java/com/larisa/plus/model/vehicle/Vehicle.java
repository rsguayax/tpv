package com.larisa.plus.model.vehicle;

import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Vehicle extends BaseEntity {

    private String brand;
    private String model;
    private int year;
    private int cylinder;

    @ManyToOne
    @JoinColumn(name = "fuel")
    private ItemCatalog fuel;

    private Date register;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, ItemCatalog fuel, Date register) {
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.register = register;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }

    public ItemCatalog getFuel() {
        return fuel;
    }

    public void setFuel(ItemCatalog fuel) {
        this.fuel = fuel;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
