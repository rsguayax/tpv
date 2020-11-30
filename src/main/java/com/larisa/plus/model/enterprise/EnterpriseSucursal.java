package com.larisa.plus.model.enterprise;

import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.Ubication;
import com.larisa.plus.model.user.User;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EnterpriseSucursal extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "ubication")
    private Ubication ubication;
    private String photo;
    private String telephone;
    private String cellphone;
    @ManyToOne
    @JoinColumn(name = "manager")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    private Enterprise enterprise;

    private boolean status;
    private Date register;

    public EnterpriseSucursal() {
    }

    public Ubication getUbication() {
        return ubication;
    }

    public void setUbication(Ubication ubication) {
        this.ubication = ubication;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
