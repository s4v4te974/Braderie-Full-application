package com.springangularbraderie.service;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.springangularbraderie.entity.Account;
import com.springangularbraderie.entity.Article;
import com.springangularbraderie.entity.Panier;
import com.springangularbraderie.service.serviceimpl.PanierServiceImpl;

@SpringBootTest
@Sql({"/schema.sql", "/data.sql"})
class PanierServiceImplTest {

	@Autowired
	PanierServiceImpl panierService;

	@BeforeEach
	void init() {

		Account insertedUserBeforeTest = buildAccount();

		panierService.deleteAll(insertedUserBeforeTest.getIdUser());

		Article insertedArticlebeforeTestBonbon = buildListArticle().get(0);

		panierService.insertArticle(insertedUserBeforeTest, insertedArticlebeforeTestBonbon, 5);
	}

	@Test
	void insertArticleTest() {

		Account inputAccount = buildAccount();

		Article insertedArticleBonbon = Article.builder() //
				.idArticle(1) //
				.description("Bonbon") //
				.marque("Haribo") //
				.prix(0) //
				.build(); //

		int input = 5;
		int quantityExpected = 10;

		Article insertedArticleStylo = buildListArticle().get(1);

		panierService.insertArticle(inputAccount, insertedArticleBonbon, input);
		panierService.insertArticle(inputAccount, insertedArticleStylo, input);

		List<Panier> outputPaniers = panierService.getListArticle(buildAccount().getIdUser());
		List<Panier> expectedPanier = new ArrayList<>();

		Panier bonbon = Panier.builder() //
				.idPanier(1) //
				.article(insertedArticleBonbon) //
				.user(inputAccount) //
				.quantite(quantityExpected) //
				.build(); //

		Panier stylo = Panier.builder() //
				.idPanier(2) //
				.article(insertedArticleStylo) //
				.user(inputAccount) //
				.quantite(input) //
				.build();

		expectedPanier.add(bonbon);
		expectedPanier.add(stylo);

		assertIterableEquals(outputPaniers, expectedPanier);
	}

	@Test
	void removeArticleTest() {

		Article toBeRemoved = buildListArticle().get(0);

		Account inputUser = buildAccount();

		int beforeDelete = panierService.getListArticle(inputUser.getIdUser()).size();

		panierService.removeArticle(toBeRemoved.getIdArticle(), inputUser);

		int afterDelete = panierService.getListArticle(inputUser.getIdUser()).size();

		assertNotEquals(beforeDelete, afterDelete);
	}

	private Account buildAccount() {
		return Account.builder() //
				.idUser(1) //
				.login("Marleyb") //
				.pass("marleyb123") //
				.role("user") //
				.build(); //
	}

	private List<Article> buildListArticle() {
		List<Article> listArticles = new ArrayList<>();

		Article Bonbon = Article.builder() //
				.idArticle(1) //
				.description("Bonbon") //
				.marque("Haribo") //
				.prix(0) //
				.build(); //

		Article Stylo = Article.builder() //
				.idArticle(2) //
				.description("Stylo Bleu") //
				.marque("Bic") //
				.prix(1) //
				.build(); //

		listArticles.add(Bonbon);
		listArticles.add(Stylo);

		return listArticles;
	}
}
