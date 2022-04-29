/**
 * Package Service
 */
package com.springangularbraderie.service;

import com.springangularbraderie.entity.Account;

/**
 * @author JRSS
 * Interface de userService
 */
public interface IUserService {
	
	/**
	 * permet de logger un User
	 * @param login {@link String}
	 * @param pass {@link String}
	 * @return account {@link Account}
	 */
	Account enableTolog(String login, String pass);
}
