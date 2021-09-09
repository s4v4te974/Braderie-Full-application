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
 * @author Stephane Kouotze CDA7
 *
 */

@Service
public class ArticleService implements IArticleService{

	@Autowired
	IArticleRep hArticleRep;

	@Override
	public List<Article> getAllArticle() {

		List<Article> lArticle = null;

		lArticle = (List<Article>) hArticleRep.findAll();

		return lArticle;
	}

	@Override
	public Optional<Article> getArticle(int idArticle) {
		
		Optional<Article> hArticle = null;

		hArticle = hArticleRep.findById(idArticle);

		return hArticle;
	}

}


