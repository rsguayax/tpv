package com.larisa.plus.model.article_vehicle;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.vehicle.Vehicle;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ArticleVehicle extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    private String description;

    private int units;

    /**
     * this field should be item_catalog
     * CATEGORY, should be: an article have many category
     */
    @ManyToOne
    @JoinColumn(name = "type_filter")
    private ItemCatalog type_filter;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    @JoinColumn(name = "time_change")
    private Integer time_change;

    private Date register;

    public ArticleVehicle() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ItemCatalog getType_filter() {
        return type_filter;
    }

    public void setType_filter(ItemCatalog type_filter) {
        this.type_filter = type_filter;
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

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTime_change() {
        return time_change;
    }

    public void setTime_change(Integer time_change) {
        this.time_change = time_change;
    }
}
