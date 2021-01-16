package com.larisa.plus.model.enterprise;

import org.springframework.data.repository.CrudRepository;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long> {

    Enterprise findByCode(String code);
}
