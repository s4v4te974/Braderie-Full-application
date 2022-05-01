/**
 * Package Service
 */
package com.springangularbraderie.service.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IUserRep;
import com.springangularbraderie.service.IUserService;


/**
 * @author JRSS
 * Manipulation des Users
 */

@Service
public class UserServiceImpl implements IUserService{

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
	 * @param user {@link Integer}
	 * @return Utilisateur {@link Account}
	 */
	public Optional<Account> findByIdUser(int iduser) {
		return hUserRep.findById(iduser);
	}
}
