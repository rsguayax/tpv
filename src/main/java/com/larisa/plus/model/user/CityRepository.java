package com.larisa.plus.model.user;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    City findById(int city);

}
