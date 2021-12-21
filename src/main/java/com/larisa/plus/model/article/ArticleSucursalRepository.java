package com.larisa.plus.model.article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleSucursalRepository extends CrudRepository<ArticleSucursal, Long> {
    /*Buscar artículos dentro de una sucursal*/
    @Query("SELECT a FROM ArticleSucursal a WHERE (a.code like CONCAT('%',:code,'%') OR a.name like CONCAT('%',:name,'%')) AND a.sucursal.id =:id ORDER BY a.name")
    public List<ArticleSucursal> getArticles(@Param("code") String code, @Param("name") String name, @Param("id") int id);

    ArticleSucursal findByArticle_IdAndSucursal_Id(int article, int sucursal);

    ArticleSucursal findByArticle_CodeAndSucursal_Id(String code, int sucursal);

    ArticleSucursal findById(int id);
}
