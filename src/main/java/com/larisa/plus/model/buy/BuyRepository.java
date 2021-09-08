package com.larisa.plus.model.buy;

import com.larisa.plus.model.article.ArticleSucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyRepository extends CrudRepository<Buy, Long> {

    //@Query("SELECT a FROM Buy a WHERE a.sucursal.id =:id ORDER BY a.register DESC")
    public List<Buy> getBuyBySucursal_Id( @Param("id") int id );

    Buy getBuyById( int id );

}
