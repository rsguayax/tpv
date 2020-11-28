package com.larisa.plus.model.user;

import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class City extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    private String name;
    private String description;
    private String lat;
    private String lng;
    private Date register;

    public City(Country country, String name, String description, String lat, String lng, Date register) {
        this.country = country;
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.register = register;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
