package com.larisa.plus.model.user;

import org.springframework.data.repository.CrudRepository;

public interface UserEnterpriseRepository extends CrudRepository<UserEnterprise, Long> {

    UserEnterprise findByUser_IdAndEnterprise_Id(int user, int enterprise);

}