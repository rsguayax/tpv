package com.larisa.plus.model.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParroquiaRepository extends CrudRepository<Parroquia, Long> {

    Parroquia findById(int parroquia);

    List<Parroquia> findByCanton_Id(int canton);

}
