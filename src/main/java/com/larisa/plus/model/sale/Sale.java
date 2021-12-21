package com.larisa.plus.model.sale;

import com.larisa.plus.model.cashier.Cashier;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.client.Client;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Sucursal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/*  VENTA   */
@Entity
public class Sale extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "cashier")
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "document_type")
    private ItemCatalog document_type;

    private String document_serie;

    private String document_num;

    @Column(precision=10, scale=2)
    private double iva;

    @Column(precision=10, scale=2)
    private double total;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    /*Esta fecha se guarda cuando se realiza la venta, ya que previo a la venta el registro puede ser solo una cotización
    * Cuando se trata solo de una cotización, no se afecta el stock de los artículos.
    * */
    private Date sale_date;
    private Date quote_date;
    private Date register;

    @ManyToOne
    @JoinColumn(name = "way_pay")
    private ItemCatalog way_pay;

    public Sale() {
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ItemCatalog getDocument_type() {
        return document_type;
    }

    public void setDocument_type(ItemCatalog document_type) {
        this.document_type = document_type;
    }

    public String getDocument_serie() {
        return document_serie;
    }

    public void setDocument_serie(String document_serie) {
        this.document_serie = document_serie;
    }

    public String getDocument_num() {
        return document_num;
    }

    public void setDocument_num(String document_num) {
        this.document_num = document_num;
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

    public ItemCatalog getStatus() {
        return status;
    }

    public void setStatus(ItemCatalog status) {
        this.status = status;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public Date getQuote_date() {
        return quote_date;
    }

    public void setQuote_date(Date quote_date) {
        this.quote_date = quote_date;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public ItemCatalog getWay_pay() {
        return way_pay;
    }

    public void setWay_pay(ItemCatalog way_pay) {
        this.way_pay = way_pay;
    }
}
