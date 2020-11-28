package com.larisa.plus.model.catalog;

import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ItemCatalog extends BaseEntity {

    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "catalog")
    private Catalog catalog;
    private boolean status;
    private Date register;

    public ItemCatalog(String name, String description, Catalog catalog, boolean status, Date register) {
        this.name = name;
        this.description = description;
        this.catalog = catalog;
        this.status = status;
        this.register = register;
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

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
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
