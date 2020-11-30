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

    /**
     * We use too for show alarms when there are a few units
     */
    private int stock;


    private Date expirationDate;

    private boolean status;
    /**
     * User that make register of article
     */
    private Date register;

    public ArticleProvider() {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
