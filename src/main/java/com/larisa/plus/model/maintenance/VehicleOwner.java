package com.larisa.plus.model.maintenance;

import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class VehicleOwner extends BaseEntity {

    private String code;
    private String plaque;
    private String colour;

    @ManyToOne
    @JoinColumn(name = "type")
    private ItemCatalog type;

    /*Dueño del vehículo*/
    @ManyToOne
    @JoinColumn(name = "owner")
    private Client owner;

    private Date register;

    public VehicleOwner() {
    }

    public VehicleOwner(String code, String plaque, String colour, ItemCatalog type, Client owner, Date register) {
        this.code = code;
        this.plaque = plaque;
        this.colour = colour;
        this.type = type;
        this.owner = owner;
        this.register = register;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public ItemCatalog getType() {
        return type;
    }

    public void setType(ItemCatalog type) {
        this.type = type;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
