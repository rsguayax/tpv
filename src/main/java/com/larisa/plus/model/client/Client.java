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

    private boolean status;
    private Date register;

    public Client() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
