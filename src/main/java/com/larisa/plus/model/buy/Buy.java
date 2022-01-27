package com.larisa.plus.model.buy;

import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Sucursal;
import com.larisa.plus.model.provider.Provider;
import com.larisa.plus.model.user.User;
import com.larisa.plus.model.user.UserEnterprise;

import javax.persistence.*;
import java.util.Date;

/*  COMPRA   */
@Entity
public class Buy extends BaseEntity {

    private String document_number;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "provider", nullable = true)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "sucursal", nullable = true)
    private Sucursal sucursal;

    @JoinColumn(name = "buy_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date buyDate;

    @Column(precision=10, scale=2)
    private double iva;

    @Column(precision=10, scale=2)
    private double total;

    @ManyToOne
    @JoinColumn(name = "document_type")
    private ItemCatalog document_type;

    @ManyToOne
    @JoinColumn(name = "status", nullable = true)
    private ItemCatalog status;

    @ManyToOne
    @JoinColumn(name = "user_enterprise", nullable = true)
    private UserEnterprise user_enterprise;

    private String observation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date register;

    public Buy() {
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ItemCatalog getDocument_type() {
        return document_type;
    }

    public void setDocument_type(ItemCatalog document_type) {
        this.document_type = document_type;
    }

    public ItemCatalog getStatus() {
        return status;
    }

    public void setStatus(ItemCatalog status) {
        this.status = status;
    }

    public UserEnterprise getUser_enterprise() {
        return user_enterprise;
    }

    public void setUser_enterprise(UserEnterprise user_enterprise) {
        this.user_enterprise = user_enterprise;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}

