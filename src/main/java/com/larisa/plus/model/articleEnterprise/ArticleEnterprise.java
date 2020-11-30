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
public class ArticleEnterprise extends BaseEntity {

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
    @JoinColumn(name = "enterprise")
    private Enterprise enterprise;
    /**
     * User that make register of article
     */
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private Date register;

}
