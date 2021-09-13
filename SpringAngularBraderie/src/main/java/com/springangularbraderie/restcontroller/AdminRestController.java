/**
 * Package Rest
 */
package com.springangularbraderie.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.service.AdminService;
import com.springangularbraderie.service.PanierService;

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
	
	@Autowired
	PanierService panierService;

	/**
	 * Permet de persister un article 
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	
	@PostMapping(path = "/createArticle", consumes="application/json")
	public Article createArticle(@RequestBody Article p_Article) {

		// verifier que l'article n'existe pas déjà
		Article hArticle = adminService.createArticleAdmin(p_Article);

		return hArticle;			

	}

	/**
	 * permet d'updater les attributs d'un article
	 * @param p_Article {@link Article}
	 * @return article {@link Article}
	 */
	@PutMapping(path = "/updateArticle")
	public Article updateArticle(@RequestBody Article p_Article) {

		Article uArticle = adminService.updateArticleAdmin(p_Article);

		return uArticle;
	}
	
	/**
	 * Permet de supprimer un article de la base de données
	 * @param idArticle {@link Article}
	 */
	@DeleteMapping(path="/removeAdmin/{id}")
	public void deleteArticle(@PathVariable("id") Integer idArticle) {
		
	// supprimer les lignes de panier qui ont le meme ID
		
	panierService.deleteArticle(idArticle);
		
	adminService.removeArticleAdmin(idArticle);
		
	}
}
