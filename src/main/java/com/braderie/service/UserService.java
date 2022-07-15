/**
 * Package Service
 */
package com.braderie.service;

import com.braderie.entity.Account;

/**
 * @author JRSS
 * Interface de userService
 */
public interface UserService {
	
	/**
	 * permet de logger un User
	 * @param login {@link String}
	 * @param pass {@link String}
	 * @return account {@link Account}
	 */
	Account enableTolog(String login, String pass);
}
