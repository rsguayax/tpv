package com.larisa.plus.model.client;

import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Client extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private Date register;


}
