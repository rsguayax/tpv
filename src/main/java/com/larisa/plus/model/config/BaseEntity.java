package com.larisa.plus.model.config;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Basic(optional = false)
    //@Column(name = "id", updatable = false, nullable = false)
    //@NotNull
    //private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="id", insertable = true, updatable = true, nullable=false)
    private int id;

    protected BaseEntity() {
        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
