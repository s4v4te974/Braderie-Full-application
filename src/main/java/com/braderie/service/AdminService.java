/**
 * Package Service
 */
package com.braderie.service;

import com.braderie.entity.Article;

/**
 * @author JRSS
 * Manipulation des Articles
 */
public interface AdminService {

	Article updateArticleAdmin(Article article);
	
	void removeArticleAdmin(Integer idArticle);
	
	void deleteArticle(Integer idArticle);
	
}
