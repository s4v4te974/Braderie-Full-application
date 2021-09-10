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

import com.springangularbraderie.model.Account;
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

	@Autowired
	IUserService hUserService;
	
	@Autowired
	IArticleService hArticleService;

	/**
	 * Permet de logger un User
	 * @param json
	 * @return User {@link User}
	 */
	@PostMapping(path="/user", produces= "application/json")
	public Account getCurrentConnectUser(@RequestBody Account user) {
		
		Account p_user = hUserService.enableTolog(user.getLogin(), user.getPass());
	
		log.info("user authentifié : " + p_user);
		
		return p_user;
	}
}