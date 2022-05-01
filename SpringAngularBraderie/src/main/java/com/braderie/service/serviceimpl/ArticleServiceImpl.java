/**
 * Package Service
 */
package com.braderie.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braderie.entity.Article;
import com.braderie.repository.IArticleRep;
import com.braderie.service.IArticleService;

/**
 * @author JRSS
 * Manipulation des Articles
 */

@Service
public class ArticleServiceImpl implements IArticleService{

	@Autowired
	IArticleRep hArticleRep;

	/**
	 * Permet de retourner la liste des articles
	 * @return list Panier {@link List} {@link Article}
	 */
	@Override
	public List<Article> getAllArticle() {
		return hArticleRep.findAll();
	}

	/**
	 * Permet de retourner la liste des articles
	 * @param idArticle {@link Integer}
	 * @return list Panier {@link Optional} {@link Article}
	 */
	@Override
	public Article getArticle(int idArticle) {
		
		Optional<Article> getOneArticle = hArticleRep.findById(idArticle);
		
		if(getOneArticle.isPresent()) {
			return getOneArticle.get();
		}else {
			return null;
		}
	}
}