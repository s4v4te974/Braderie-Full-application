/**
 * 
 */
package com.springangularbraderie.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangularbraderie.model.User;
import com.springangularbraderie.service.IArticleService;
import com.springangularbraderie.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Stephane Kouotze CDA7
 *
 */

@Slf4j
@RestController
@RequestMapping("/index")
public class UserRestController {

	/**
	 * 
	 */

	@Autowired
	IUserService hUserService;
	
	@Autowired
	IArticleService hArticleService;
	
	
	@GetMapping(path="/user", produces= "application/json")
	public User getCurrentConnectUser(User p_user) {
		
		p_user = hUserService.enableTolog(p_user.getLogin(), p_user.getPass());
	
		log.info("user authentifié : " + p_user);
		
		return p_user;
	}

}