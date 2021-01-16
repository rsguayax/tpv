package com.larisa.plus.model.articleEnterprise;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Enterprise;
import com.larisa.plus.model.enterprise.EnterpriseSucursal;
import com.larisa.plus.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ArticleSucursal extends BaseEntity {

    private String name;
    private String description;
    /**
     * This field we use for online buy
     */
    private String photo;

    /**
     * this field should be item_catalog
     *  the measure can be: caneca, galón, cuarto, litro, etc.
     */
    @ManyToOne
    @JoinColumn(name = "measure")
    private ItemCatalog measure;

    /*Create for this date, one list of marks as: Mobil, Amalie, etc.*/
    @ManyToOne
    @JoinColumn(name = "mark")
    private ItemCatalog mark;

    /**
     * this field should be item_catalog
     *  CATEGORY, should be: an article have many category
     */
    @ManyToOne
    @JoinColumn(name = "category")
    private ItemCatalog category;

    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private EnterpriseSucursal sucursal;
    /**
     * User that make register of article
     */
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private Date register;

    public ArticleSucursal() {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ItemCatalog getMeasure() {
        return measure;
    }

    public void setMeasure(ItemCatalog measure) {
        this.measure = measure;
    }

    public ItemCatalog getMark() {
        return mark;
    }

    public void setMark(ItemCatalog mark) {
        this.mark = mark;
    }

    public ItemCatalog getCategory() {
        return category;
    }

    public void setCategory(ItemCatalog category) {
        this.category = category;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public EnterpriseSucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(EnterpriseSucursal sucursal) {
        this.sucursal = sucursal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
