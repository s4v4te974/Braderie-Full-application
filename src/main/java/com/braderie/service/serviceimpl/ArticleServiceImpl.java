/**
 * Package Service
 */
package com.braderie.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braderie.entity.Article;
import com.braderie.repository.ArticleRepository;
import com.braderie.service.ArticleService;

/**
 * @author JRSS
 * Manipulation des Articles
 */

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepository hArticleRep;

	@Override
	public List<Article> getAllArticle() {
		return hArticleRep.findAll();
	}

	@Override
	public Article getArticle(int idArticle) {
		return hArticleRep.findById(idArticle).orElse(null);
	}
}