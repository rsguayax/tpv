package com.larisa.plus.model.article;

import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.user.User;
import com.larisa.plus.model.catalog.ItemCatalog;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Article extends BaseEntity {

    private String code;
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

    private Date register;

    public Article() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
