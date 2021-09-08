package com.larisa.plus.model.enterprise;

import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.SocialWeb;
import com.larisa.plus.model.user.Ubication;
import com.larisa.plus.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Enterprise extends BaseEntity {

    private String code;
    private String name;
    private String description;
    private String photo;
    private String logo;
    private String slogan;
    /**
     * This field, should be catalog SRI activities
     */
    private String actividad;
    private String vision;
    private String mission;

    @ManyToOne
    @JoinColumn(name = "social_web")
    private SocialWeb socialWeb;

    @ManyToOne
    @JoinColumn(name = "manager")
    private User manager;

    private boolean status;
    private Date register;

    public Enterprise() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public SocialWeb getSocialWeb() {
        return socialWeb;
    }

    public void setSocialWeb(SocialWeb socialWeb) {
        this.socialWeb = socialWeb;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
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
