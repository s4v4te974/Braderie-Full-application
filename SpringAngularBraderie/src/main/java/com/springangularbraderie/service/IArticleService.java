/**
 * Package Service
 */
package com.springangularbraderie.service;

import java.util.List;
import java.util.Optional;

import com.springangularbraderie.entity.Article;

/**
 * @author JRSS
 * Interface de ArticleService
 */

public interface IArticleService {
	
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