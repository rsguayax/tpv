package com.larisa.plus.model.catalog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemCatalogRepository extends CrudRepository<ItemCatalog, Long> {

    ItemCatalog findById(int id);
    List<ItemCatalog> findByCatalog_Id(int id);

}
