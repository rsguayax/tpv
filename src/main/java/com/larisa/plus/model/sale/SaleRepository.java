package com.larisa.plus.model.sale;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    //List<Sale> findBySale_date(Date fecha);
}
