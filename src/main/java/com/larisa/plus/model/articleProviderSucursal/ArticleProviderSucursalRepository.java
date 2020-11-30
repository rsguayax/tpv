package com.larisa.plus.model.articleProviderSucursal;

import com.larisa.plus.model.articleProvider.ArticleProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleProviderSucursalRepository extends CrudRepository<ArticleProviderSucursal, Long> {

    @Query("SELECT a.articleProvider FROM ArticleProviderSucursal a WHERE a.enterpriseSucursal.id = :id")
    public List<ArticleProvider> getArticleProviderSucursal(@Param("id") int id);
}
