package com.larisa.plus.model.sale;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.article.ArticleSucursal;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Sucursal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class SaleDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "sale")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "article_sucursal")
    private ArticleSucursal article_sucursal;

    private int quantity;

    @Column(precision=10, scale=2)
    private double sale_price;

    @Column(precision=10, scale=2)
    private double discount;

    private Date register;

    public SaleDetail() {
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
