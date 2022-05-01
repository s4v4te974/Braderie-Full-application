/**
 * Package Service
 */
package com.braderie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.braderie.entity.Article;
import com.braderie.service.serviceimpl.AdminServiceImpl;
import com.braderie.service.serviceimpl.ArticleServiceImpl;

/**
 * @author JRSS Classe Service de l'admin
 *
 */

@SpringBootTest
@Sql({"/schema.sql", "/data.sql"})
class AdminServiceImplTest {

	@Autowired
	ArticleServiceImpl articleService;

	@Autowired
	AdminServiceImpl adminService;

	@Test
	void createArticleAdminTest() {

		Article toBeSaved = buildArticle();
		toBeSaved.setIdArticle(8);

		Article input = adminService.createArticleAdmin(toBeSaved);

		assertEquals(input.getIdArticle(), toBeSaved.getIdArticle());
		assertEquals(input.getDescription(), toBeSaved.getDescription());
		assertEquals(input.getMarque(), toBeSaved.getMarque());
		assertEquals(input.getPrix(), toBeSaved.getPrix());
	}

	@Test
	void updateArticleAdminTest() {

		Article toBeUpdate = buildArticle();
		toBeUpdate.setIdArticle(1);

		Article input = adminService.updateArticleAdmin(toBeUpdate);

		assertEquals(input.getIdArticle(), toBeUpdate.getIdArticle());
		assertEquals(input.getDescription(), toBeUpdate.getDescription());
		assertEquals(input.getMarque(), toBeUpdate.getMarque());
		assertEquals(input.getPrix(), toBeUpdate.getPrix());
	}

	@Test
	void removeArticleAdminTest() {
		
		int idArticle = 8;
		
		Article toBeDeleted = buildArticle();
		
		toBeDeleted.setIdArticle(idArticle);
		
		adminService.createArticleAdmin(toBeDeleted);
		
		int beforeSize = listArticleSize();
		
		adminService.removeArticleAdmin(idArticle);
		
		int afterSize = listArticleSize();
		
		assertNotEquals(beforeSize, afterSize);
	}

	private Article buildArticle() {
		return Article.builder() //
				.description("Description") //
				.marque("Marque") //
				.prix(20) //
				.build(); //
	}

	private int listArticleSize() {
		List<Article> listsArticle = articleService.getAllArticle();
		return listsArticle.size();
	}
}
