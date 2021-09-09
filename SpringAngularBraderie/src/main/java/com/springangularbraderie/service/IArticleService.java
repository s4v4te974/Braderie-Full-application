/**
 * Package Service
 */
package com.springangularbraderie.service;

import java.util.List;
import java.util.Optional;

import com.springangularbraderie.model.Article;

/**
 * @author dangs
 *
 */
public interface IArticleService {
	
	public List<Article> getAllArticle();
	
	public Optional<Article> getArticle(int idArticle);

}
