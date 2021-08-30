/**
 * 
 */
package com.springangularbraderie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.User;
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
	public User enableTolog(String login, String pass) {
		// TODO Gerer les exception
		return hUserRep.getByLoginAndPass(login, pass);
	}
	
	
	public Optional<User> findByIdUser(int p_idUser) {
		
		Optional<User> hUser = null;
		
		hUser = hUserRep.findById(p_idUser);
		
		return hUser;
	}
}
