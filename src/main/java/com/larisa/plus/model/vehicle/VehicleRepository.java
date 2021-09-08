package com.larisa.plus.model.vehicle;

import com.larisa.plus.model.article.Article;
import com.larisa.plus.model.article_vehicle.ArticleVehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findById(int id);

    @Query("SELECT a FROM Vehicle a WHERE (a.brand like CONCAT('%',:brand,'%') OR a.model like CONCAT('%',:model,'%')) ORDER BY a.brand")
    public List<Vehicle> findByBrandOrModel(@Param("brand") String band, @Param("model") String model);
}
