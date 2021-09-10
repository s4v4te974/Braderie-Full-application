/**
 * Package Service
 */
package com.springangularbraderie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.repository.IArticleRep;

/**
 * @author JRSS
 * Manipulation des Articles
 */

@Service
public class ArticleService implements IArticleService{

	@Autowired
	IArticleRep hArticleRep;

	/**
	 * Permet de retourner la liste des articles
	 * @return list Panier {@link List} {@link Article}
	 */
	
	@Override
	public List<Article> getAllArticle() {

		List<Article> lArticle = null;

		lArticle = (List<Article>) hArticleRep.findAll();

		return lArticle;
	}

	/**
	 * Permet de retourner la liste des articles
	 * @param idArticle {@link Integer}
	 * @return list Panier {@link Optional} {@link Article}
	 */
	@Override
	public Optional<Article> getArticle(int idArticle) {
		
		Optional<Article> hArticle = null;

		hArticle = hArticleRep.findById(idArticle);

		return hArticle;
	}

}