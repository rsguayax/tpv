package com.larisa.plus.model.article;

import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Sucursal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ArticleSucursal extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private Sucursal sucursal;

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

    /*this field will know calculate the total gain in this buy*/
/*    @Column(precision=10, scale=2)
    private double sale_price;*/
    @Column(nullable= true, precision=10, scale=4)    // Creates the database field with this size.
    private BigDecimal sale_price;

    @Column(nullable= true, precision=10, scale=4)    // Creates the database field with this size.
    private BigDecimal buy_price;

/*    @Column(precision=10, scale=2)
    private double discount;*/
    @Column(nullable= true, precision=10, scale=2)    // Creates the database field with this size.
    private BigDecimal discount;
    /**
     * We use too for show alarms when there are a few units
     */
    private int stock;

    @ManyToOne
    @JoinColumn(name = "status")
    private ItemCatalog status;

    private Date register;

    public ArticleSucursal() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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

    public BigDecimal getSale_price() {
        return sale_price;
    }

    public void setSale_price(BigDecimal sale_price) {
        this.sale_price = sale_price;
    }

    public BigDecimal getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(BigDecimal buy_price) {
        this.buy_price = buy_price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
}
