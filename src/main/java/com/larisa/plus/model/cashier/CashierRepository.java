package com.larisa.plus.model.cashier;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CashierRepository extends CrudRepository<Cashier, Long> {

    //@Query("SELECT a FROM Client a WHERE a.id = :id ")
    Cashier findById(int id);
}
