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
    private double buySale;
    /**
     * We use too for show alarms when there are a few units
     */
    private int stock;
    private Date buyDate;

    /**
     * this field should be item_catalog
     *  CATEGORY, should be: an article have many category
     */
    @ManyToOne
    @JoinColumn(name = "measure")
    private ItemCatalog measure;

    @ManyToOne
    @JoinColumn(name = "mark")
    private ItemCatalog mark;

    @ManyToOne
    @JoinColumn(name = "category")
    private ItemCatalog category;

    private double discount;
    private Date dateBeginDiscount;
    private Date dateEndDiscount;
    private String observation;
    /**
     * when entered an article, need know, if price have iva or not, for can calculate the buySale
     */
    private boolean iva;
    /**
     * By default status is TRUE, but if status is FALSE, only if it's down
     */
    private boolean status;
    /**
     * User that make register of article
     */
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private Date register;

    public Article(String code, String name, String description, double buySale, int stock, Date buyDate, ItemCatalog measure, ItemCatalog mark, ItemCatalog category, boolean iva, boolean status, User user, Date register) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.buySale = buySale;
        this.stock = stock;
        this.buyDate = buyDate;
        this.measure = measure;
        this.mark = mark;
        this.category = category;
        this.iva = iva;
        this.status = status;
        this.user = user;
        this.register = register;
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

    public double getBuySale() {
        return buySale;
    }

    public void setBuySale(double buySale) {
        this.buySale = buySale;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getDateBeginDiscount() {
        return dateBeginDiscount;
    }

    public void setDateBeginDiscount(Date dateBeginDiscount) {
        this.dateBeginDiscount = dateBeginDiscount;
    }

    public Date getDateEndDiscount() {
        return dateEndDiscount;
    }

    public void setDateEndDiscount(Date dateEndDiscount) {
        this.dateEndDiscount = dateEndDiscount;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public boolean isIva() {
        return iva;
    }

    public void setIva(boolean iva) {
        this.iva = iva;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
