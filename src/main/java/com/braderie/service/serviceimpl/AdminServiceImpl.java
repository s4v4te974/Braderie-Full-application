/**
 * Package Service
 */
package com.braderie.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braderie.entity.Article;
import com.braderie.entity.Panier;
import com.braderie.repository.IAdminRep;
import com.braderie.repository.IArticleRep;
import com.braderie.repository.IPanierRep;
import com.braderie.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS Classe Service de l'admin
 *
 */
@Service
@Slf4j
public class AdminServiceImpl implements IAdminService {
	
	private static final String CREATE_ARTICLE_SUCCESS = "Article créé et insérer dans la base de données";
	
	private static final String UPDATE_ARTICLE_SUCCESS = "Article mise à jour dans la base de données";
	
	private static final String DELETE_ARTICLE_SUCCESS = " Article supprimé de la base de données";

	@Autowired
	IAdminRep adminRep;

	@Autowired
	IPanierRep hPanierRep;

	@Autowired
	ArticleServiceImpl articleService;

	@Autowired
	IArticleRep articleRep;

	/**
	 * Permet de persister un article
	 * 
	 * @param article {@link Article}
	 * @return article {@link Article}
	 */
	@Transactional
	public Article createArticleAdmin(Article article) {

		if (article != null) {
			Article createArticle = Article.builder() //
					.description(article.getDescription()) //
					.marque(article.getMarque()) //
					.prix(article.getPrix()) //
					.build();

			adminRep.save(createArticle);

			log.info(CREATE_ARTICLE_SUCCESS);

			return createArticle;

		} else {
			return null;
		}
	}

	/**
	 * permet d'updater les attributs d'un article
	 * 
	 * @param article {@link Article}
	 * @return article {@link Article}
	 */
	public Article updateArticleAdmin(Article article) {

		if(article != null) {
			// recupere l'article par son ID
			Integer idArticle = article.getIdArticle();

			// recuperer 'article dans la BDD correspondant
			Article articleBdd = articleService.getArticle(idArticle);

			// on set les nouvelle definition
			articleBdd.setDescription(article.getDescription());
			articleBdd.setMarque(article.getMarque());
			articleBdd.setPrix(article.getPrix());

			// on le sauve dans la BDD
			adminRep.save(articleBdd);

			log.info(UPDATE_ARTICLE_SUCCESS);

			return articleBdd;
		}else {
			return null;
		}
		
	}

	/**
	 * Permet de supprimer un article de la base de données
	 * 
	 * @param idArticle {@link Integer}
	 */
	public void removeArticleAdmin(Integer idArticle) {

		Article articleToRemove = articleService.getArticle(idArticle);

		if (articleToRemove != null) {
			articleRep.delete(articleToRemove);
		}

		log.info(DELETE_ARTICLE_SUCCESS);
	}

	/**
	 * Permet de supprimer toutes les lignes de paniers via l'id Article
	 * 
	 * @param idArticle {@link Article}
	 */
	public void deleteArticle(Integer idArticle) {
		List<Panier> lPaniers = hPanierRep.deletePanierByIDArticle(idArticle);
		hPanierRep.deleteAll(lPaniers);
	}
}
