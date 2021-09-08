package com.larisa.plus.model.provider;

import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.SocialWeb;
import com.larisa.plus.model.user.Ubication;
import com.larisa.plus.model.user.User;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.user.UserEnterprise;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Provider extends BaseEntity {
    /*La lista de proveedores de cada sucursal, lo sacamos de las compras*/

    @ManyToOne
    @JoinColumn(name = "user_enterprise")
    private UserEnterprise user_enterprise;

    /**
     * Get list Activities of RUc in SRI
     */
    @ManyToOne
    @JoinColumn(name = "service")
    private ItemCatalog service;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    private Date register;

    public Provider() {
    }

    public UserEnterprise getUser_enterprise() {
        return user_enterprise;
    }

    public void setUser_enterprise(UserEnterprise user_enterprise) {
        this.user_enterprise = user_enterprise;
    }

    public ItemCatalog getService() {
        return service;
    }

    public void setService(ItemCatalog service) {
        this.service = service;
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
