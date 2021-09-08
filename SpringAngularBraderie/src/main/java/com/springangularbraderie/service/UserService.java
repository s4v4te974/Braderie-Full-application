/**
 * 
 */
package com.springangularbraderie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IUserRep;


/**
 * @author Stephane Kouotze CDA7
 *
 */

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRep hUserRep;

	@Override
	public Account enableTolog(String login, String pass) {
		// TODO Gerer les exception
		return hUserRep.getByLoginAndPass(login, pass);
	}
	
	
	public Optional<Account> findByIdUser(int p_idUser) {
		
		Optional<Account> hUser = null;
		
		hUser = hUserRep.findById(p_idUser);
		
		return hUser;
	}
}
