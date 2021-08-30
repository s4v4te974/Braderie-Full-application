/**
 * 
 */
package com.springangularbraderie.restcontroller;

import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@SessionAttributes("user")
@Controller
public class MagasinController {

	@Autowired
	HttpSession session;

	@Autowired
	ArticleService hArticleService;

	@Autowired
	PanierService hPanierService;

	@Autowired
	UserService userService;

	@Autowired
	UserController userController;

	@GetMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		session.invalidate();

		log.info("Déconnexion OK");
		return "redirect:index"; 
	}


	@PostMapping("/addArticle")
	public String addCaddie(@RequestParam(name = "idArticle" ) int idArticle, 
			@RequestParam(name = "quantite") int p_Quantite,
			Model model) {

		User hUser= (User) session.getAttribute("user");

		Article hArticle = hArticleService.getArticle(idArticle).get();

		@SuppressWarnings("unchecked")
		List<Panier> listPanierBeforeAdd = (List<Panier>) session.getAttribute("listPanier");

		List<Panier> listPanierResult =  hPanierService.addCadie(listPanierBeforeAdd, hUser, hArticle, p_Quantite);	

		session.setAttribute("ListPanier", listPanierResult);

		afficherInfo(model);

		model.addAttribute("prixTotal", hPanierService.getPrixLigne());

		return "magasin";
	}


	@PostMapping("/deleteArticle")
	public String deleteCaddie(@RequestParam(name = "deleteArticle" ) Integer idArticle,
			Model model) {

		log.info("idArticle à supprimer" + idArticle);

		/* utilisation de ListIterator pour parcourir notre liste
		le soucis : quand on boucler avec une boucle for, des que l'on supprimé un element ,la taile de la liste diminuer (-1) 
	 	conséquence = la boucle ayant un nombre définit de base ( liste.size()) celle ci boucler encore malgrès la diminution de sa taille
	 	1er exemple for(int i = 0, i < list.size(), i++){   = premier passage , nombre de passage défini = 8 et liste.size() vaut 8 // deuxieme passage nombre de passage défini = 8 et liste. size() vaut 7 il va donc boucler une fois de trop 
	 				remove(i)                           = on supprime une ligne 

		 *************************** resultat IndexOutOfBound *********************************

	 	ListIreator permet de boucler sur une liste, mais offre les methodes hasNext() ( boolean) et .next() objet suivant
	 	on peut donc iterer avec un while list.next qui s'arrete automatiquement à la fin de la liste, peut importe le nombde de remove et add 	(merci le boolean) 

		 */

		@SuppressWarnings("unchecked")
		List<Panier> listPanier = (List<Panier>) session.getAttribute("listPanier");

		// declaration de l'iterator
		ListIterator<Panier> listIterator = listPanier.listIterator();

		//boucle while .hasnext ( boolean qui vérifie le suivant
		while(listIterator.hasNext()) {

			// récupération de l'id en cours et comparaison avec notre parametre
			if(listIterator.next().getArticle().getIdArticle() == idArticle) {

				// on le remove
				listIterator.remove();
			}
		}

		session.setAttribute("listPanier", listPanier);

		afficherInfo(model);

		return "magasin";
	}


	@PostMapping("/savePanier")
	public String savePanier(Model model) {

		User hUser= (User) session.getAttribute("user");

		@SuppressWarnings("unchecked")
		List<Panier> listPanier = (List<Panier>) session.getAttribute("listPanier");

		for (Panier panier : listPanier) {	
			hPanierService.insertArticle(hUser, panier.getArticle(), panier.getQuantite());
		}

		List<Panier> listPanierBDD = hPanierService.setPrixListPanier(hUser);

		model.addAttribute("listPanier", listPanierBDD);

		// Calcul du prix total du panier 
		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());

		model.addAttribute("prixTotal", prixTotal);

		return "caddie";
	}


	@GetMapping("/clear")
	public String deleteCaddie(Model model) {

		User hUser= (User) session.getAttribute("user");

		// Suppression de la liste de panier du User dans la BDD
		hPanierService.deleteAll(hUser.getIdUser());

		// Récupération de la liste de panier de la BDD (qui doit être vide)
		List<Panier> listeCaddie = hPanierService.getListArticle(hUser.getIdUser());

		log.info("Panier supprimé de la BDD, taille du panier : " + listeCaddie.size());

		model.addAttribute("listPanier", listeCaddie);

		return "caddie";
	}


	@GetMapping("/return")
	public String returnToMagasin(Model model) {

		afficherInfo(model);

		return "magasin";
	}

	@PostMapping("/restore")
	public String restore(Model model) {

		User hUser= (User) session.getAttribute("user");

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listeCaddie = hPanierService.setPrixListPanier(hUser);	

		model.addAttribute("listPanier", listeCaddie);

		Integer prixTotal = hPanierService.totalPanier(hUser.getIdUser());

		model.addAttribute("prixTotal", prixTotal);

		return "caddie";
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


	public void afficherInfo(Model model) {

		@SuppressWarnings("unchecked")
		final List<Article> LISTEARTICLE = (List<Article>) session.getAttribute("listArticle");

		@SuppressWarnings("unchecked")
		List<Panier> listPanier = (List<Panier>) session.getAttribute("listPanier");

		model.addAttribute("listArticle", LISTEARTICLE);

		model.addAttribute("listPanier", listPanier);

	}




}