/**
 * Package Service
 */
package com.springangularbraderie.service;

import com.springangularbraderie.entity.Article;

/**
 * @author JRSS
 * Manipulation des Articles
 */
public interface IAdminService {

	Article updateArticleAdmin(Article article);
	
	void removeArticleAdmin(Integer idArticle);
	
	void deleteArticle(Integer idArticle);
	
}
