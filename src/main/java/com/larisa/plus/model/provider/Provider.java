package com.larisa.plus.model.provider;

import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.Ubication;
import com.larisa.plus.model.user.User;
import com.larisa.plus.model.catalog.ItemCatalog;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class Provider extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user")
    //@JsonBackReference
    private User user;

    /**
     * Get list Activities of RUc in SRI
     */
    @ManyToOne
    @JoinColumn(name = "measure")
    private ItemCatalog servicio;

    @ManyToOne
    @JoinColumn(name="ubication")
    private Ubication ubication;

    private boolean status;
    private Date register;

    public Provider() {
    }

    public Provider(ItemCatalog servicio, Ubication ubication, boolean status) {
        this.servicio = servicio;
        this.ubication = ubication;
        this.status = status;
    }

    public Provider(User user, Date register) {
        this.user = user;
        this.register = register;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ItemCatalog getServicio() {
        return servicio;
    }

    public void setServicio(ItemCatalog servicio) {
        this.servicio = servicio;
    }

    public Ubication getUbication() {
        return ubication;
    }

    public void setUbication(Ubication ubication) {
        this.ubication = ubication;
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
