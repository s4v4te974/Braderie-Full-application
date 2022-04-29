package com.springangularbraderie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.springangularbraderie.entity.Account;
import com.springangularbraderie.service.serviceimpl.UserServiceImpl;

@SpringBootTest
@Sql({"/schema.sql", "/data.sql"})
class UserServiceImplTest {

	@Autowired
	UserServiceImpl userService;

	
	@Test
	void enableToLogTest() {
		
		Account expected = accountForTest();
		
		Account input = userService.enableTolog(expected.getLogin(), expected.getPass());
		
		assertEquals(input.getLogin(), expected.getLogin());
		assertEquals(input.getPass(), input.getPass());
	}
	
	@Test
	void findByIdUserTest(){
		
		Account expected = accountForTest();
		
		int user = 1;
		
		Account input = userService.findByIdUser(user);
		
		assertNotNull(input);
		assertEquals(input.getLogin(), expected.getLogin());
		assertEquals(input.getPass(), expected.getPass());
		assertEquals(input.getIdUser(), user);
	}
	
	private Account accountForTest() {
		String name = "Marleyb";
		String pass = "marleyb123";
		
		return Account.builder() //
						   .login(name) //
						   .pass(pass) //
						   .build(); //
	}
}
