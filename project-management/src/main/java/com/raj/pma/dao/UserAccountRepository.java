package com.raj.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.raj.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository <UserAccount, Long> {

}
