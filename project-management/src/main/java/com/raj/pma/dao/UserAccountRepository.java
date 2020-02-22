package com.raj.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.raj.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
