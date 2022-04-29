package com.springangularbraderie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.springangularbraderie.entity.Article;
import com.springangularbraderie.service.serviceimpl.ArticleServiceImpl;

@SpringBootTest
@Sql({"/schema.sql", "/data.sql"})
class ArticleServiceImplTest {
	
	@Autowired
	ArticleServiceImpl articleService;

	@Test
	void getArticleTest() {
		
		List<Article> listArticles = buildListArticles();
		
		Article expected = listArticles.get(0);
		
		Article input = articleService.getArticle(expected.getIdArticle());
		
		assertEquals(input, expected);
	}
	
	@Test
	void getAllArticleTest() {
		
		List<Article> listArticles = buildListArticles();
		
		List<Article> inputList = articleService.getAllArticle();
		
		assertIterableEquals(listArticles, inputList);
		
	}

	private List<Article> buildListArticles() {

		List<Article> listArticles = new ArrayList<>();
		
		Article bonbon = Article.builder() //
				.idArticle(1) //
				.description("Bonbon") //
				.marque("Haribo") //
				.prix(0) //
				.build(); //
		
		Article stylo = Article.builder() //
				.idArticle(2) //
				.description("Stylo Bleu") //
				.marque("Bic") //
				.prix(1) //
				.build(); //
		
		Article ramette = Article.builder() //
				.idArticle(3) //
				.description("Ramette 80 grs") //
				.marque("ClaireFontaine") //
				.prix(5) //
				.build(); //
		
		Article equerre = Article.builder() //
				.idArticle(4) //
				.description("Equerre") //
				.marque("Castorama") //
				.prix(2) //
				.build(); //
		
		Article montre = Article.builder() //
				.idArticle(5) //
				.description("Montre") //
				.marque("Rolex") //
				.prix(200) //
				.build(); //
		
		Article velo = Article.builder() //
				.idArticle(6) //
				.description("Velo") //
				.marque("Decathlon") //
				.prix(190) //
				.build(); //
		
		Article rameur = Article.builder() //
				.idArticle(7) //
				.description("Rameur") //
				.marque("Decathlon") //
				.prix(290) //
				.build(); //
		
		listArticles.add(bonbon);
		listArticles.add(stylo);
		listArticles.add(ramette);
		listArticles.add(equerre);
		listArticles.add(montre);
		listArticles.add(velo);
		listArticles.add(rameur);
		
		return listArticles;
	}
}
