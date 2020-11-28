package com.larisa.plus.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User extends BaseEntity {

    private String name;
    private String lastName;
    private String email;
    private String username;
    private String identifier;
    private String password;
    private String photo;
    private Date lastaccess;

    private String telephone;
    private String cellphone;

    @OneToOne
    @JoinColumn(name = "ubication")
    //@JsonBackReference
    private Ubication ubication;

    @OneToOne
    @JoinColumn(name = "social_web")
    //@JsonBackReference
    private SocialWeb socialWeb;

    private Date register;

    public User() {
    }

    public User(String name, String lastName, String email, String username, String password, String photo){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.setPhoto(photo);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public Date getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Date lastaccess) {
        this.lastaccess = lastaccess;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    public Ubication getUbication() {
        return ubication;
    }

    public void setUbication(Ubication ubication) {
        this.ubication = ubication;
    }

    public SocialWeb getSocialWeb() {
        return socialWeb;
    }

    public void setSocialWeb(SocialWeb socialWeb) {
        this.socialWeb = socialWeb;
    }
}
