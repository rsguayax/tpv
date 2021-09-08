package com.larisa.plus.model.user;

import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Enterprise;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

/*Los usuarios pueden repetirse, pero entre distintas empresas, sin embargo se conserva el enlace al usuario base*/
@Entity
public class UserEnterprise extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    private Enterprise enterprise;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    private String name;
    @JoinColumn(name = "lastName")
    private String lastName;
    private String email;
    private String identifier;
    private String photo;

    private String telephone;
    private String cellphone;

    private Date register;

    @OneToOne
    @JoinColumn(name = "ubication")
    //@JsonBackReference
    private Ubication ubication;

    public UserEnterprise() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public ItemCatalog getStatus() {
        return status;
    }

    public void setStatus(ItemCatalog status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public Ubication getUbication() {
        return ubication;
    }

    public void setUbication(Ubication ubication) {
        this.ubication = ubication;
    }


}
