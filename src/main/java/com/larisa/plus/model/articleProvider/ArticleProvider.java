package com.larisa.plus.model.articleProvider;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.provider.Provider;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ArticleProvider extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "article")
    //@JsonBackReference
    private Article article;

    @ManyToOne
    @JoinColumn(name = "provider")
    //@JsonBackReference
    private Provider provider;

    private double buyPrice;
    /**
     * This field, add units a field stock of model Article.  So, two smile articles but with diferent provider add a field stock
     */
    private int buyUnit;

    private Date buyDate;
    private Date expirationDate;
    /**
     * User that make register of article
     */
    private Date register;

    public ArticleProvider(Article article, Provider provider, double buyPrice, int buyUnit, Date buyDate, Date register) {
        this.article = article;
        this.provider = provider;
        this.buyPrice = buyPrice;
        this.buyUnit = buyUnit;
        this.buyDate = buyDate;
        this.register = register;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(int buyUnit) {
        this.buyUnit = buyUnit;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
