package com.springangularbraderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.model.Account;

@Repository
public interface IUserRep extends JpaRepository<Account, Integer> {
	
	Account getByLoginAndPass(String login, String pass);
	
	Account findOneByLogin(String login);
	
}
