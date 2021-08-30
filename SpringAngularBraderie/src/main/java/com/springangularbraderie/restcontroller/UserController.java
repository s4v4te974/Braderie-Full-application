/**
 * 
 */
package com.springangularbraderie.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.User;
import com.springangularbraderie.service.IArticleService;
import com.springangularbraderie.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Stephane Kouotze CDA7
 *
 */

@Slf4j
@Controller
public class UserController {

	/**
	 * 
	 */
	@Autowired
	HttpSession session;
	
	@Autowired
	IUserService hUserService;
	
	@Autowired
	IArticleService hArticleService;
	
	@Value("${error.message}")
	String errorMessage;

	// Methode get permet de récuperer et affciher la page
	@GetMapping({"/","/index"})
	public String tryToLog(@ModelAttribute User user) {
		
		return "index";
	}
	
	// methode post permet de recuperer les informations du formulaire de la page
	// ModelTrribute permet de récuperer les attributs passer par le form
	// appele de la methode de login
	// verification et retour
	@PostMapping({"/","/index"})
	public String getCurrentConnectUser(@ModelAttribute("user")User p_user, Model model) {
		
		p_user = hUserService.enableTolog(p_user.getLogin(), p_user.getPass());
		
		List<Article> listArticle = hArticleService.getAllArticle();
		
		List<Panier> listPanier = new ArrayList<Panier>();
		
		session.setAttribute("user", p_user);
		
		session.setAttribute("listPanier", listPanier);
		
		session.setAttribute("listArticle", listArticle);	
		
		if(p_user == null) {
			model.addAttribute("errorMessage", errorMessage);
			return "index";
		}
		
		model.addAttribute("listArticle", listArticle);
		
		log.info("Authentification du user OK");
		
		return "magasin";
	}

}