/**
 * 
 */
package com.springangularbraderie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.User;
import com.springangularbraderie.repository.IPanierRep;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Stephane Kouotze CDA7
 *
 */

@Data
@Service
@Slf4j
public class PanierService implements IPanierService{

	private Integer prixLigne = 0;

	@Autowired
	IPanierRep hPanierRep;

	@Autowired
	ArticleService hArticleService;
	

	/**
	 * Méthode qui met à jour la quantité d'une liggne de panier
	 * @param iduser
	 * @param quantite
	 * @param idArticle
	 * @return
	 */
	public Panier updatePanier(int iduser, int quantite, int idArticle) {

		Panier panier = null;

		panier = hPanierRep.getByUserAndArticle(iduser, idArticle);

		panier.setQuantite(quantite);

		hPanierRep.save(panier);

		return panier; 
	}

	/**
	 * Méthode qui insère une ligne de panier dans le panier d'un User ou met à jour la quantité 
	 * si l'article est déjà présent dans son panier
	 * @param UserRestController p_user, Article p_article, int quantite
	 */

	private boolean verifIdArticleBase(int p_user, int idArticle, int quantite){

		boolean resultat = false;
		//création de la liste qui va stocker tous les éléments récupérer de la bdd
		List<Panier> lPanier = new ArrayList<Panier>();

		//récupéreration des éléments
		lPanier = hPanierRep.getListPaniers(p_user);

		//on parcourt la liste récupérer 
		for (int i = 0; i < lPanier.size(); i++) {
			//s'il y a un IdArticle qui existe déjà dans la bdd alors maj de la quantité en additionnant 
			//la quantité à rajouté et la quantité presente dans la bdd
			if (lPanier.get(i).getArticle().getIdArticle() == idArticle) {
				resultat = true;
			}
		}

		log.info("OK - verifIdArticleBase");

		return resultat;
	}

	@Override
	public Panier insertArticle(User p_user, Article p_article, int quantite) {

		Panier hPanierAdded = null;

		//utilisation de la methode verifIdArticleBase qui vérifie si l'idArticle existe déjà
		//retourne true s'il est dans la base
		boolean verifArticleBDD = verifIdArticleBase(p_user.getIdUser(), p_article.getIdArticle(), quantite);
		
		log.info("current user" + p_user);

		//si l' idArticle n'existe pas dans la BDD de la table panier
		if (verifArticleBDD == false) {
			
			//Création d'une ligne panier avec les paramètres initiales
			Panier hPanier = new Panier(p_user, p_article, quantite);

			//insertion de la ligne dans la bdd
			hPanierAdded = hPanierRep.save(hPanier);

			log.info("OK - insertArticle()");

			// sinon mise à jour de la quantité de la ligne de panier 	
		} else {

			log.info("current user" + p_user);
			
			// récupération de l'ancienne quantité
			Integer oldQuantite = hPanierRep.getByUserAndArticle(p_user.getIdUser(), p_article.getIdArticle()).getQuantite();

			int newQuantite = oldQuantite + quantite;

			//modification de la ligne dans la bdd
			hPanierAdded = updatePanier(p_user.getIdUser(), newQuantite, p_article.getIdArticle());

			log.info("OK - updateArticle(int idUser, int idArticle, int quantite)");
		}
		return hPanierAdded;
	}

	/**
	 * Méthode qui supprime la liste de panier appartenant à un User
	 * @param idUser
	 */
	@Override
	public void deleteAll(int idUser) {

		// récupération de la liste de panier du user IdUser
		List<Panier> lPanier = hPanierRep.getListPaniers(idUser);

		// delete ce panier récupéré
		hPanierRep.deleteAll(lPanier);

		log.info("OK - deleteAll()");
	}

	/**
	 * Méthode qui récupère une liste de Panier appartenant à un User
	 * @param p_id
	 * @return une liste de Paniers
	 */
	@Override
	public List<Panier> getListArticle(int p_id) {

		List<Panier> hArticlePanier = hPanierRep.getListPaniers(p_id);

		log.info("OK - List<Panier> getListArticle(int p_user)");

		return hArticlePanier;	
	}

	/**
	 * Methode qui permet de verifier suivant un idArticle donné s'il est déjà présent dans la base 
	 * s'il est présent il retourne true sinon false
	 * @param idArticle
	 * @param quantite
	 * @return
	 */


	public void savePanier(List<Panier> lPanier) {
		hPanierRep.saveAll(lPanier);
	}


	public Integer totalPanier(int p_id) {

		Integer totalPrice = 0;

		List<Panier> listPaniers = hPanierRep.getListPaniers(p_id);

		for (Panier panier : listPaniers) {
			totalPrice += panier.getArticle().getPrix() * panier.getQuantite();
		}

		log.info("le total est" + totalPrice);

		return totalPrice;
	}


	//////// externalisation de la methode addCaddie
	public List<Panier> addCadie(List<Panier> p_lPanier, User p_user, Article p_article, int p_quantite){

		boolean bIdTrouve = false;

		List<Panier> lPanier = p_lPanier;

		Integer idArticle = p_article.getIdArticle();

		// Parcours la liste de Panier 
		for(int i = 0; i < lPanier.size(); i++) {

			// si notre idArticle est déjà présent dans la liste panier 
			if(lPanier.get(i).getArticle().getIdArticle() == idArticle) {

				// mise à jour de la quantité de l'article contenu dans le panier
				Integer quantiteUpdate = lPanier.get(i).getQuantite() + p_quantite;
				lPanier.get(i).setQuantite(quantiteUpdate);

				// calcul du prix de la ligne avec la nouvelle quantité settée et on set le prix dans la ligne de panier
				prixLigne = lPanier.get(i).getArticle().getPrix() * lPanier.get(i).getQuantite();
				lPanier.get(i).setPrix(prixLigne);

				log.info("quantité et prix de la ligne de panier mis à jour : " + p_lPanier.get(i).toString());

				// passe le boolean à true
				bIdTrouve = true;
			}
		} // FIN FOR

		//si le boolean n'a pas changé à true alors
		//cela signifie que l'article n'existe pas dans mon panier 
		if (!bIdTrouve) {

			// Création d'une nouvelle ligne de panier avec les informations récupérées dans le form
			// et ajout dans la liste de panier
			Panier newPanier = new Panier(p_user, p_article, p_quantite);
			lPanier.add(newPanier);

			// Calcul du prix de la ligne et on le set
			prixLigne = newPanier.getArticle().getPrix() * newPanier.getQuantite();
			newPanier.setPrix(prixLigne);

			log.info("ajout nouvel article dans la liste : " + newPanier.toString());
		}
		return p_lPanier;
	}

	public List<Panier> setPrixListPanier(User hUser){

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listePanier = hPanierRep.getListPaniers(hUser.getIdUser());

		// Parcours la liste pour calculer et setter le prix de la ligne 
		for (Panier panier : listePanier) {
			int prixLigne = panier.getArticle().getPrix() * panier.getQuantite();
			panier.setPrix(prixLigne);
		}

		return listePanier;
	}
	
	public void removeArticle(Integer idArticle, User p_user) {
		
		Panier hPanier = hPanierRep.getByUserAndArticle(p_user.getIdUser(), idArticle);
		
		hPanierRep.delete(hPanier);
	}

}
