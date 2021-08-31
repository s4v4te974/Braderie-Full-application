/**
 * 
 */
package com.springangularbraderie.restcontroller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		log.info("Article ajouté : " + hArticle);

		return hArticle;
	}


	
	@PostMapping(path="/savePanier", consumes= "application/json")
	public List<Panier> savePanier(@RequestBody List<Panier> p_lPanier) {

		for (Panier panier : p_lPanier) {	
			User p_user = panier.getUser();
			Article p_article= panier.getArticle();
			hPanierService.insertArticle(p_user, p_article, panier.getQuantite());
		}
			
		log.info("Panier sauvegardé : " + p_lPanier);	

		return p_lPanier;
	}



	@DeleteMapping(path="/clear/{id}")
    public void deleteCaddie(@PathVariable("id") int idUser) {
        
        // Suppression de la liste de panier du User dans la BDD
        hPanierService.deleteAll(idUser);

 

        // Récupération de la liste de panier de la BDD (qui doit être vide)
        List<Panier> listeCaddie = hPanierService.getListArticle(idUser);

 

        log.info("Panier supprimé, taille du panier : " + listeCaddie.size());
    }


	
	@GetMapping(path="/getListPanier", produces= "application/json")
	public List<Panier> restore(int idUser) {
		
		User hUser = hUserService.findByIdUser(idUser).get();

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		@SuppressWarnings("unused")
		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());

		log.info("Panier récupéré - taille du panier : " + listeCaddie.size() + ", du user : " + hUser);
		
		return listeCaddie;
	}

}