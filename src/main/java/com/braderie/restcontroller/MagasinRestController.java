/**
 * Package Rest
 */
package com.braderie.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braderie.entity.Account;
import com.braderie.entity.Article;
import com.braderie.entity.Panier;
import com.braderie.service.serviceimpl.ArticleServiceImpl;
import com.braderie.service.serviceimpl.PanierServiceImpl;
import com.braderie.service.serviceimpl.UserServiceImpl;

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

	@GetMapping(path="/getAllArticle", produces= "application/json")
	public List<Article> getAllArticle() {
		return hArticleService.getAllArticle();
	}

	@GetMapping(path="/getArticle", produces= "application/json")
	public Article getArticleById(int idArticle) {

		Article hArticle = hArticleService.getArticle(idArticle);

		log.info(ARTICLE_ADD, hArticle);

		return hArticle;
	}

	@PostMapping(path="/savePanier", consumes= "application/json")
	public List<Panier> savePanier(@RequestBody List<Panier> lPanier) {

		hPanierService.deleteAll(lPanier.get(0).getUser().getIdUser());
		
		lPanier.stream().forEach(panier -> {
			hPanierService.insertArticle(panier.getUser(), panier.getArticle(), panier.getQuantite());
		});

		log.info(ARTICLE_SAVE, lPanier);	

		return lPanier;
	}

	@DeleteMapping(path="/clear/{id}")
	public void deletePanier(@PathVariable("id") int idUser) {

		hPanierService.deleteAll(idUser);

		List<Panier> listeCaddie = hPanierService.getListArticle(idUser);

		log.info(DELETE_CADDIE, listeCaddie.size());
	}

	@GetMapping(path="/getListPanier", produces= "application/json")
	public List<Panier> restore(int idUser) {

		Account hUser = hUserService.findByIdUser(idUser);

		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		log.info(GET_PANIER, listeCaddie.size() + SPECIFIC_USER ,hUser);

		return listeCaddie;
	}
	
	@DeleteMapping(path="/removeArticle")
	public void removeBDD(@RequestBody Panier panier) {

		hPanierService.removeArticle(panier.getArticle().getIdArticle(), panier.getUser());

		log.info(DELETE_ARTICLE, panier.getArticle().getIdArticle());
	}
}