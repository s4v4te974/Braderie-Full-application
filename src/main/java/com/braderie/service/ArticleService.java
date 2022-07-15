/**
 * Package Service
 */
package com.braderie.service;

import java.util.List;
import java.util.Optional;

import com.braderie.entity.Article;

/**
 * @author JRSS
 * Interface de ArticleService
 */

public interface ArticleService {
	
	/**
	 * Permet de retourner la liste des articles
	 * @return list Panier {@link List} {@link Article}
	 */
	public List<Article> getAllArticle();
	
	/**
	 * Permet de retourner la liste des articles
	 * @param idArticle {@link Integer}
	 * @return list Panier {@link Optional} {@link Article}
	 */
	public Article getArticle(int idArticle);

}