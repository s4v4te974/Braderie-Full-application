/**
 * Package Rest
 */
package com.springangularbraderie.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.service.serviceimpl.ArticleServiceImpl;
import com.springangularbraderie.service.serviceimpl.PanierServiceImpl;
import com.springangularbraderie.service.serviceimpl.UserServiceImpl;
import com.springangularbraderie.model.Account;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS
 * Restful Caddie
 */
@Slf4j
@RestController
@RequestMapping("/caddie")
@CrossOrigin(origins ="*")
public class CaddieRestController {

	@Autowired
	ArticleServiceImpl hArticleService;

	@Autowired
	PanierServiceImpl hPanierService;

	@Autowired
	UserServiceImpl hUserService;

	/**
	 * Permet de supprimer un article sauvegarder par un User dans la base de données Panier
	 * @param panier {@link Panier}
	 */
	@DeleteMapping(path="/removeArticle")
	// récupère grâce  pathVariable l'idArticle 
	public void removeBDD(@RequestBody Panier panier) {

		Account hUser = panier.getUser();
		int idArticle = panier.getArticle().getIdArticle(); 

		hPanierService.removeArticle(idArticle, hUser);

		// Récupération de la liste de panier dans la BDD 
		@SuppressWarnings("unused")
		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		@SuppressWarnings("unused")
		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());	

		log.info("iDarticle supprimé : " + idArticle);
	}
}