package com.larisa.plus.model.cashier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Sucursal;
import com.larisa.plus.model.user.UserEnterprise;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Cashier extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_enterprise")
    private UserEnterprise user_enterprise;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    private Date register;

    public Cashier() {
    }

    public Cashier(UserEnterprise user_enterprise, Sucursal sucursal, ItemCatalog status, Date register) {
        this.user_enterprise = user_enterprise;
        this.sucursal = sucursal;
        this.status = status;
        this.register = register;
    }

    public UserEnterprise getUser_enterprise() {
        return user_enterprise;
    }

    public void setUser_enterprise(UserEnterprise user_enterprise) {
        this.user_enterprise = user_enterprise;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public ItemCatalog getStatus() {
        return status;
    }

    public void setStatus(ItemCatalog status) {
        this.status = status;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
