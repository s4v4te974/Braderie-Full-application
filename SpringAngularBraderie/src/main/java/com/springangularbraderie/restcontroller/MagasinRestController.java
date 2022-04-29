/**
 * Package Rest
 */
package com.springangularbraderie.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangularbraderie.entity.Account;
import com.springangularbraderie.entity.Article;
import com.springangularbraderie.entity.Panier;
import com.springangularbraderie.service.serviceimpl.ArticleServiceImpl;
import com.springangularbraderie.service.serviceimpl.PanierServiceImpl;
import com.springangularbraderie.service.serviceimpl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS
 * Restful Magasin
 */

@Slf4j
@RestController
@RequestMapping("/magasin")
@CrossOrigin(origins ="*")
public class MagasinRestController {
	
	private static final String ARTICLE_ADD = "Article ajouté : {}";
	
	private static final String ARTICLE_SAVE = "Panier sauvegardé : {}";
	
	private static final String DELETE_CADDIE = "Panier supprimé, taille du panier : {}";
	
	private static final String GET_PANIER = "Panier récupéré - taille du panier : {}";
	
	private static final String SPECIFIC_USER = ", du user : {}";
	
	private static final String DELETE_ARTICLE = "Article supprimé : {}";

	@Autowired
	ArticleServiceImpl hArticleService;

	@Autowired
	PanierServiceImpl hPanierService;

	@Autowired
	UserServiceImpl hUserService;

	/**
	 * Permet de retourner la liste des articles
	 * @return list Panier {@link List} {@link Article}
	 */
	@GetMapping(path="/getAllArticle", produces= "application/json")
	public List<Article> getAllArticle() {
		return hArticleService.getAllArticle();
	}

	/**
	 * Permet de retourner un article specifique
	 * @param idArticle {@link Integer}
	 * @return Article {@link Article}
	 */
	@GetMapping(path="/getArticle", produces= "application/json")
	public Article getArticleById(int idArticle) {

		Article hArticle = hArticleService.getArticle(idArticle);

		log.info(ARTICLE_ADD, hArticle);

		return hArticle;
	}

	/**
	 * Sauvegarde la liste de panier selon un User
	 * @param p_lPanier {@link Optional} {@link Article}
	 * @return list Panier {@link Optional} {@link Article}
	 */
	@PostMapping(path="/savePanier", consumes= "application/json")
	public List<Panier> savePanier(@RequestBody List<Panier> lPanier) {

		// Suppression de la liste de panier du User dans la BDD
		hPanierService.deleteAll(lPanier.get(0).getUser().getIdUser());

		// Parcours la liste de panier pour l'insérer dans la bdd
		for (Panier panier : lPanier) {	
			Account pUser = panier.getUser();
			Article pArticle= panier.getArticle();
			hPanierService.insertArticle(pUser, pArticle, panier.getQuantite());
		}

		log.info(ARTICLE_SAVE, lPanier);	

		return lPanier;
	}

	/**
	 * Supprime la liste de panier appartenant à un User
	 * @param idUser {@link Integer}
	 */
	@DeleteMapping(path="/clear/{id}")
	public void deletePanier(@PathVariable("id") int idUser) {

		// Suppression de la liste de panier du User dans la BDD
		hPanierService.deleteAll(idUser);

		// Récupération de la liste de panier de la BDD (qui doit être vide)
		List<Panier> listeCaddie = hPanierService.getListArticle(idUser);

		log.info(DELETE_CADDIE, listeCaddie.size());
	}

	/**
	 * Récupère une liste de Panier appartenant à un User
	 * @param idUser {@link Integer}
	 * @return list panier {@link List} {@link Panier}
	 */
	@GetMapping(path="/getListPanier", produces= "application/json")
	public List<Panier> restore(int idUser) {

		Account hUser = hUserService.findByIdUser(idUser);

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		log.info(GET_PANIER, listeCaddie.size() + SPECIFIC_USER ,hUser);

		return listeCaddie;
	}
	
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

		log.info(DELETE_ARTICLE, idArticle);
	}
}