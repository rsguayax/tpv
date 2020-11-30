package com.larisa.plus.model.buy;

import com.larisa.plus.model.articleProvider.ArticleProvider;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Buy extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "article_provider")
    private ArticleProvider articleProvider;

    private String observation;

    private Date date;

    private Date register;

}

