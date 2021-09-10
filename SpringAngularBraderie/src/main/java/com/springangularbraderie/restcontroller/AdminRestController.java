/**
 * 
 */
package com.springangularbraderie.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.service.AdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Stephane Kouotze CDA7
 *
 */
/**
 * @author kingd
 *
 */
/**
 * @author kingd
 *
 */
@Slf4j
@RestController
public class AdminRestController {

	@Autowired
	AdminService adminService;

	/**
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	
	public Article createArticle(Article p_Article) {

		Article hArticle = adminService.createArticleAdmin(p_Article);

		log.info("Article créé");

		return hArticle;			

	}

	/**
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	public Article updateArticle(Article p_Article) {

		Article uArticle = adminService.updateArticleAdmin(p_Article);

		return uArticle;

	}
	
	/**
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	public void deleteArticle(Integer idArticle) {
		
	adminService.removeArticleAdmin(idArticle);
		
	}
	// read One

	// read All  ( select - get )
}
