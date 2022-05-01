/**
 * Package Rest
 */
package com.braderie.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braderie.entity.Article;
import com.braderie.service.serviceimpl.AdminServiceImpl;
import com.braderie.service.serviceimpl.PanierServiceImpl;

/**
 * @author JRSS Restful Admin
 */

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "*")
public class AdminRestController {

	@Autowired
	AdminServiceImpl adminService;

	@Autowired
	PanierServiceImpl panierService;

	/**
	 * Permet de persister un article
	 * verifier que l'article n'existe pas déjà
	 * 
	 * @param article {@link Article}
	 * @return article {@link Article}
	 */

	@PostMapping(path = "/createArticle", consumes = "application/json")
	public Article createArticle(@RequestBody Article article) {

		return adminService.createArticleAdmin(article);

	}

	/**
	 * permet d'updater les attributs d'un article
	 * 
	 * @param article {@link Article}
	 * @return article {@link Article}
	 */
	@PutMapping(path = "/updateArticle")
	public Article updateArticle(@RequestBody Article article) {

		return adminService.updateArticleAdmin(article);

	}

	/**
	 * Permet de supprimer un article de la base de données
	 * 
	 * @param idArticle {@link Article}
	 */
	@DeleteMapping(path = "/removeAdmin/{id}")
	public void deleteArticle(@PathVariable("id") Integer idArticle) {

		// supprimer les lignes de panier qui ont le meme ID

		adminService.deleteArticle(idArticle);

		adminService.removeArticleAdmin(idArticle);

	}
}
