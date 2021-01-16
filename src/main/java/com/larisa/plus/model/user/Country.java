package com.larisa.plus.model.user;

import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Country extends BaseEntity {

    private String name;
    private String description;
    private Date register;

    public Country() {
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
}
