package com.larisa.plus.model.provider;

import com.larisa.plus.model.client.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderRepository extends CrudRepository<Provider, Long> {
    @Query("SELECT a FROM Provider a WHERE a.user_enterprise.identifier like CONCAT('%',:param,'%') OR a.user_enterprise.name like CONCAT('%',:param,'%') ")
    List<Provider> findByIdentifierOrName(String param);

    @Query("SELECT a FROM Provider a WHERE a.id = :id ")
    Provider findById(int id);
}
