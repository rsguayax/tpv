package com.larisa.plus.model.buy;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class BuyDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "buy")
    private Buy buy;

    @ManyToOne
    @JoinColumn(name = "article_sucursal")
    private ArticleSucursal article_sucursal;

    //this field is for save of date expiration the article
    private Date expiration_date;
    /**
     * This field, add units a field stock of model Article.  So, two smile articles but with diferent provider add a field stock
     */
    private int quantity;

    @Column(precision=10, scale=2)
    private double buy_price;

    @Column(precision=10, scale=2)
    private double iva;

    private Date register;

    public BuyDetail() {
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public ArticleSucursal getArticle_sucursal() {
        return article_sucursal;
    }

    public void setArticle_sucursal(ArticleSucursal article_sucursal) {
        this.article_sucursal = article_sucursal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(double buy_price) {
        this.buy_price = buy_price;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
