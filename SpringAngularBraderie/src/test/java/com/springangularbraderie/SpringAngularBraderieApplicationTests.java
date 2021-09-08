package com.springangularbraderie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IUserRep;

@SpringBootTest
class SpringAngularBraderieApplicationTests {
	
	@Autowired
	IUserRep userRep;

	@Test
	void contextLoads() {
	}
	
	@Test
	void findOneByLogin() {
		
		Account hUser = userRep.findOneByLogin("Marleyb");
		
		Integer idInteger = 1;
		
		assertThat(hUser.getIdUser()).isEqualTo(idInteger);
		
	}

}
