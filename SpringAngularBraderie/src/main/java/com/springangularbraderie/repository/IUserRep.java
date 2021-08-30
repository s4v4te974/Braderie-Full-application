package com.springangularbraderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.model.User;

@Repository
public interface IUserRep extends JpaRepository<User, Integer> {
	
	//Integer countByLoginAndPass(String login, String pass);

	User getByLoginAndPass(String login, String pass);
	
}
