/**
 * 
 */
package com.springangularbraderie.restcontroller;


import java.util.Map;

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
 * @author Stephane Kouotze CDA7
 *
 */

@Slf4j
@RestController
@RequestMapping("/index")
@CrossOrigin(origins ="*")
public class UserRestController {

	/**
	 * 
	 */

	@Autowired
	IUserService hUserService;
	
	@Autowired
	IArticleService hArticleService;

	@PostMapping(path="/user", produces= "application/json")
	public Account getCurrentConnectUser(@RequestBody Map<String, String> json) {
		
		String login = json.get("login");
		
		String pass = json.get("pass");
		
		Account p_user = hUserService.enableTolog(login, pass);
	
		log.info("user authentifié : " + p_user);
		
		return p_user;
	}
	
}