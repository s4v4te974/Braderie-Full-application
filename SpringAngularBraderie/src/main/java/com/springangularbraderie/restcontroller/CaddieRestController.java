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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/caddie")
public class CaddieRestController {
	
	@Autowired
	HttpSession session;

	@Autowired
	ArticleService hArticleService;

	@Autowired
	PanierService hPanierService;

	@Autowired
	UserService hUserService;

//	
//	@DeleteMapping(path="/removeBDD")
//	// récupère grâce  pathVariable l'idArticle 
//	public void removeBDD(@RequestBody Panier p_panier) {
//
//		User hUser = p_panier.getUser();
//		int idArticle = p_panier.getArticle().getIdArticle(); 
//
//		hPanierService.removeArticle(idArticle, hUser);
//
//		// Récupération de la liste de panier dans la BDD 
//		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	
//
//		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());	
//
//	}

}