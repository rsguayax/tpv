package com.larisa.plus.model.enterprise;

import org.springframework.data.repository.CrudRepository;

public interface SucursalRepository extends CrudRepository<Sucursal, Long> {
    Sucursal findById(int id);
}
