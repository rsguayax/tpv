package com.larisa.plus.model.maintenance;

import com.larisa.plus.model.cashier.Cashier;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Sucursal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Maintenance extends BaseEntity {
    private Date date;
    private int km;
    @ManyToOne
    @JoinColumn(name = "vehicle")
    private VehicleOwner vehicle;

    /*Desde qué usuario se registra el mantenimiento*/
    @ManyToOne
    @JoinColumn(name = "cashier")
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private Sucursal sucursal;

    /*Persona encargada de realizar el mantenimiento*/
    @ManyToOne
    @JoinColumn(name = "mandated")
    private Client mandated;

    private String observation;
    private Date register;

    public Maintenance() {
    }

    public Maintenance(Date date, int km, VehicleOwner vehicle, Cashier cashier, Sucursal sucursal, Client mandated, String observation, Date register) {
        this.date = date;
        this.km = km;
        this.vehicle = vehicle;
        this.cashier = cashier;
        this.sucursal = sucursal;
        this.mandated = mandated;
        this.observation = observation;
        this.register = register;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public VehicleOwner getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleOwner vehicle) {
        this.vehicle = vehicle;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Client getMandated() {
        return mandated;
    }

    public void setMandated(Client mandated) {
        this.mandated = mandated;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
