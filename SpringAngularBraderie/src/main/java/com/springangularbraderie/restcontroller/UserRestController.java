/**
 * Package Rest
 */
package com.springangularbraderie.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangularbraderie.entity.Account;
import com.springangularbraderie.service.IArticleService;
import com.springangularbraderie.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS
 * Restful User
 */

@Slf4j
@RestController
@RequestMapping("/index")
@CrossOrigin(origins ="*")
public class UserRestController {
	
	private static final String CURRENT_ACCOUNT = "user authentifié : {} ";

	@Autowired
	IUserService hUserService;
	
	@Autowired
	IArticleService hArticleService;

	/**
	 * Permet de logger un User
	 * @param user {@link Account}
	 * @return User {@link Account}
	 */
	@PostMapping(path="/user", produces= "application/json")
	public Account getCurrentConnectUser(@RequestBody Account user) {
		
		Account currentAccount = hUserService.enableTolog(user.getLogin(), user.getPass());
	
		log.info(CURRENT_ACCOUNT, currentAccount);
		
		return currentAccount;
	}
}