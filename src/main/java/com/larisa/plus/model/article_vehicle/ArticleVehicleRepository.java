package com.larisa.plus.model.article_vehicle;

import com.larisa.plus.model.article.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleVehicleRepository extends CrudRepository<ArticleVehicle, Long> {
    ArticleVehicle findById(int id);

    List<ArticleVehicle> findByArticle_Id(int id);

}
