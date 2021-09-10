/**
 * Package Service
 */
package com.springangularbraderie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IUserRep;


/**
 * @author JRSS
 * Manipulation des Users
 */

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRep hUserRep;

	/**
	 * Permet de chercher dans la base de données si le user existe.
	 * Si il existe, retourne les informations de celui ci
	 * 
	 * @param login {@link String}
	 * @param pass {@link String}
	 * @return un [{@link Account} de la base de données
	 */
	@Override
	public Account enableTolog(String login, String pass) {
		return hUserRep.getByLoginAndPass(login, pass);
	}
	
	/**
	 * Permet de récupérer un utilisateur via son ID
	 * 
	 * @param p_idUser {@link Integer}
	 * @return Utilisateur {@link Account}
	 */
	public Optional<Account> findByIdUser(int p_idUser) {
		
		Optional<Account> hUser = null;
		
		hUser = hUserRep.findById(p_idUser);
		
		return hUser;
	}
}
