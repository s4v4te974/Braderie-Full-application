package com.springangularbraderie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IUserRep;
import com.springangularbraderie.service.serviceimpl.UserServiceImpl;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	UserServiceImpl UserService;
	
	@Autowired
	IUserRep userRep;
	
	@Test
	void enableToLogTest() {
		
		Account expected = accountForTest();
		
		Account input = UserService.enableTolog(expected.getLogin(), expected.getPass());
		
		assertEquals(input.getLogin(), expected.getLogin());
		assertEquals(input.getPass(), input.getPass());
	}
	
	@Test
	void findByIdUserTest(){
		
		Account expected = accountForTest();
		
		int user = 1;
		
		Optional<Account> input = userRep.findById(user);
		
		assertNotNull(input);
		assertEquals(input.get().getLogin(), expected.getLogin());
		assertEquals(input.get().getPass(), expected.getPass());
		assertEquals(input.get().getIdUser(), user);
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
