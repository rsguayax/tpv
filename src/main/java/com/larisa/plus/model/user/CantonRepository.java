package com.larisa.plus.model.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CantonRepository extends CrudRepository<Canton, Long> {

    Canton findById(int id);

    List<Canton> findByCity_Id(int city);

}
