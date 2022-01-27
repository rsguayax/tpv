package com.larisa.plus.model.sale;

import com.larisa.plus.model.buy.Buy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    //List<Sale> findBySale_date(Date fecha);

    //List<Sale> findBySale_date(String date);

    @Query("SELECT a FROM Sale a WHERE DATE(a.sale_date) =:date")
    List<Sale> findBySale_date(@Param("date") Date date);
}
