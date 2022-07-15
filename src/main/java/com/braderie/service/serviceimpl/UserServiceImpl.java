/**
 * Package Service
 */
package com.braderie.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braderie.entity.Account;
import com.braderie.repository.UserRepository;
import com.braderie.service.UserService;


/**
 * @author JRSS
 * Manipulation des Users
 */

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository hUserRep;

	@Override
	public Account enableTolog(String login, String pass) {
		return hUserRep.getByLoginAndPass(login, pass);
	}
	
	public Account findByIdUser(int iduser) {
		return hUserRep.findById(iduser).orElse(null);
	}
}
