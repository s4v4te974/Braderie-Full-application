/**
 * 
 */
package com.springangularbraderie.restcontroller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.User;
import com.springangularbraderie.service.ArticleService;
import com.springangularbraderie.service.PanierService;
import com.springangularbraderie.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Stephane Kouotze CDA7
 *
 */
@Slf4j
@RestController
@RequestMapping("/magasin")
public class MagasinRestController {
	
	@Autowired
	HttpSession session;

	@Autowired
	ArticleService hArticleService;

	@Autowired
	PanierService hPanierService;

	@Autowired
	UserService hUserService;


	@GetMapping(path="/getArticle", produces= "application/json")
	public Article getArticleById(int idArticle) {

		Article hArticle = hArticleService.getArticle(idArticle).get();

		return hArticle;
	}


	@PostMapping(path="/savePanier", consumes= "application/json")
	public Panier savePanier(@RequestBody Panier p_panier) {

		User p_user = p_panier.getUser();
		Article p_article= p_panier.getArticle();
		
		log.info("----------------user : " + p_user);
		log.info("-------------article : " + p_article);
		log.info("---------------quantité : " + p_panier.getQuantite());

//		for (Panier panier : p_listPanier) {	
			Panier hPanier =  hPanierService.insertArticle(p_user, p_article, p_panier.getQuantite());
//		}

		return hPanier;
	}


	@DeleteMapping(path="/clear", consumes= "application/json")
	public void deleteCaddie(@RequestBody Panier p_panier) {
		
		User hUser= p_panier.getUser();

		// Suppression de la liste de panier du User dans la BDD
		hPanierService.deleteAll(hUser.getIdUser());

		// Récupération de la liste de panier de la BDD (qui doit être vide)
		List<Panier> listeCaddie = hPanierService.getListArticle(hUser.getIdUser());

		log.info("Panier supprimé de la BDD, taille du panier : " + listeCaddie.size());
	}


	
	@GetMapping(path="/getListPanier", produces= "application/json")
	public List<Panier> restore(int idUser) {
		
		User hUser = hUserService.findByIdUser(idUser).get();

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());

		return listeCaddie;
	}

	
	@GetMapping("/removeBDD/{id}")
	// récupère grâce  pathVariable l'idArticle 
	public String removeBDD(@PathVariable("id") int idArticle, Model model) {

		log.info("idArticle à supprimer de la BDD " + idArticle);

		User hUser= (User) session.getAttribute("user");

		hPanierService.removeArticle(idArticle, hUser);

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		model.addAttribute("listPanier", listeCaddie);

		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());

		model.addAttribute("prixTotal", prixTotal);

		return "caddie";
	}

}