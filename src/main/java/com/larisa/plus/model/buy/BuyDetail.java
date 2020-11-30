package com.larisa.plus.model.buy;

import com.larisa.plus.model.articleProvider.ArticleProvider;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class BuyDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "article_provider")
    private ArticleProvider articleProvider;

    @ManyToOne
    @JoinColumn(name = "buy")
    private Buy buy;

    private double buyPrice;
    /**
     * This field, add units a field stock of model Article.  So, two smile articles but with diferent provider add a field stock
     */
    private int buyUnit;

    private Date register;

}
