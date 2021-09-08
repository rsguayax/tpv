package com.larisa.plus.model.client;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {


    @Query("SELECT a FROM Client a WHERE a.user_enterprise.identifier like CONCAT('%',:param,'%') OR a.user_enterprise.name like CONCAT('%',:param,'%') OR a.user_enterprise.lastName like CONCAT('%',:param,'%')")
    List<Client> findByIdentifierOrName(String param);

    @Query("SELECT a FROM Client a WHERE a.id = :id ")
    Client findById(int id);


}
