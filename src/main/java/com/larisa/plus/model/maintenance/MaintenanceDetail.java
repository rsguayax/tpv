package com.larisa.plus.model.maintenance;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.config.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class MaintenanceDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "maintenance")
    private Maintenance maintenance;

    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    private String observation;
    private Date register;

    public MaintenanceDetail() {
    }

    public MaintenanceDetail(Maintenance maintenance, Article article, String observation, Date register) {
        this.maintenance = maintenance;
        this.article = article;
        this.observation = observation;
        this.register = register;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
}
