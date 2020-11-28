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
    /**
     * This field, should be catalog SRI activities
     */
    private String actividad;
    private String vision;
    private String mission;

    @ManyToOne
    @JoinColumn(name = "ubication")
    private Ubication ubication;

    @ManyToOne
    @JoinColumn(name = "social_web")
    private SocialWeb socialWeb;

    @ManyToOne
    @JoinColumn(name = "manager")
    private User manager;

    private boolean status;
    private Date register;
}
