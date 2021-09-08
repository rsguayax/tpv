package com.larisa.plus.model.article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Article findByCode(String code);

    Article findById(int id);

    @Query("SELECT a FROM Article a WHERE (a.code like CONCAT('%',:code,'%') OR a.name like CONCAT('%',:name,'%')) ORDER BY a.name")
    public List<Article> getArticles(@Param("code") String code, @Param("name") String name);

}
