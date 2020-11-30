package com.larisa.plus.model.articleProviderSucursal;

import com.larisa.plus.model.articleProvider.ArticleProvider;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.EnterpriseSucursal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ArticleProviderSucursal extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "article_provider")
    private ArticleProvider articleProvider;

    @ManyToOne
    @JoinColumn(name = "sucursal")
    private EnterpriseSucursal enterpriseSucursal;

    /**
     * when entered an article, need know, if price have iva or not, for can calculate the buySale
     */
    private boolean iva;
    /**
     * By default status is TRUE, but if status is FALSE, only if it's down
     */
    private boolean status;

    private Date register;

}
