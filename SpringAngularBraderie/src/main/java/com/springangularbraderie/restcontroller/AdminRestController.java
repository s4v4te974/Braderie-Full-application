/**
 * Package Rest
 */
package com.springangularbraderie.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.service.AdminService;


/**
 * @author JRSS
 * Restful Admin
 */

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "*")
public class AdminRestController {

	@Autowired
	AdminService adminService;

	/**
	 * Permet de persister un article 
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	
	@PostMapping(path = "/createArticle", consumes="application/json")
	public Article createArticle(@RequestBody Article p_Article) {

		Article hArticle = adminService.createArticleAdmin(p_Article);

		return hArticle;			

	}

	/**
	 * permet d'updater les attributs d'un article
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	@PutMapping(path = "/updateArticle", consumes="application/json")
	public Article updateArticle(@RequestBody Article p_Article) {

		Article uArticle = adminService.updateArticleAdmin(p_Article);

		return uArticle;
	}
	
	/**
	 * Permet de supprimer un article de la base de données
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	@DeleteMapping(path="/removeAdmin")
	public void deleteArticle(@RequestBody Integer idArticle) {
		
	adminService.removeArticleAdmin(idArticle);
		
	}
}
