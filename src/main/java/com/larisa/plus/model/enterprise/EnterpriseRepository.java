package com.larisa.plus.model.enterprise;

import net.bytebuddy.asm.Advice;
import org.springframework.data.repository.CrudRepository;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long> {

    Enterprise findByCode(String code);

    Enterprise findById(int id);
}
