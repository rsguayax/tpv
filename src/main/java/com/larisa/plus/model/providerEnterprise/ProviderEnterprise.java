package com.larisa.plus.model.providerEnterprise;

import com.larisa.plus.model.catalog.ItemCatalog;
import com.larisa.plus.model.config.BaseEntity;
import com.larisa.plus.model.enterprise.Enterprise;
import com.larisa.plus.model.provider.Provider;
import com.larisa.plus.model.user.Ubication;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ProviderEnterprise extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "provider")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    private Enterprise enterprise;

    /**
     * Get list Activities of RUc in SRI
     */
    @ManyToOne
    @JoinColumn(name = "measure")
    private ItemCatalog servicio;

    @ManyToOne
    @JoinColumn(name="ubication")
    private Ubication ubication;

    /**
     * For look all providers of one enterprise
     */
    private boolean status;

    private Date register;
}
