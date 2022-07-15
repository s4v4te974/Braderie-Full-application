/**
 * Package repository
 */
package com.braderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.braderie.entity.Account;

/**
 * @author JRSS
 * Repository de gestion des User/account
 */
@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {
	
	/**
	 * permet de chercher dans la base de données un {@link Account} lié au credential 
	 * @param login {@link String}
	 * @param pass {@link String}
	 * @return un [{@link Account} de la base de données
	 */
	Account getByLoginAndPass(String login, String pass);
	
	/**
	 * permet de chercher dans la base de données un {@link Account} lié au credential 
	 * @param login {@link Integer}
	 * @return Utilisateur {@link Account}
	 */
	Account findOneByLogin(String login);
	
}
