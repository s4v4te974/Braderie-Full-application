/**
 * Package Service
 */
package com.springangularbraderie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.repository.IArticleRep;
import com.springangularbraderie.service.serviceimpl.AdminServiceImpl;

/**
 * @author JRSS
 * Classe Service de l'admin
 *
 */

@SpringBootTest
class AdminServiceImplTest {
	
	@Autowired
	IArticleRep articleRep;
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Test
	void createArticleAdminTest() {

		Article toBeSaved = Article.builder() //
							.idArticle(8) //
							.description("Description") //
							.marque("Marque") //
							.prix(0) //
							.build(); //
		
		Article input = adminService.createArticleAdmin(toBeSaved);
		
		int idArticleToBeSaved = 8;
		
		articleRep.getById(idArticleToBeSaved);
		
		assertEquals(input.getDescription(), toBeSaved.getDescription());
		
		
	}
}
