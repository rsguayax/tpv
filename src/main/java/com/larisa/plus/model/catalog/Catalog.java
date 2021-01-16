package com.larisa.plus.model.catalog;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Catalog extends BaseEntity {

    private String name;
    private String description;
    private boolean status;
    private Date register;

    public Catalog() {
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

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
