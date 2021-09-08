package com.larisa.plus.model.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.SocialWeb;
import com.larisa.plus.model.user.Ubication;
import com.larisa.plus.model.user.User;
import com.larisa.plus.model.user.UserEnterprise;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Client extends BaseEntity {

    /*La lista de clientes de una sucursal lo sacamos a partir de las ventas*/
    @ManyToOne
    @JoinColumn(name = "user_enterprise")
    private UserEnterprise user_enterprise;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    private Date register;

    public Client() {
    }

    public UserEnterprise getUser_enterprise() {
        return user_enterprise;
    }

    public void setUser_enterprise(UserEnterprise user_enterprise) {
        this.user_enterprise = user_enterprise;
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
