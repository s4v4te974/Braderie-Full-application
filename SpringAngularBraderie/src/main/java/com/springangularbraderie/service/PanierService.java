/**
 * Package Service
 */
package com.springangularbraderie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IPanierRep;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS
 * Manipulation des Paniers
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
	 * Met à jour la quantite d'une ligne de panier
	 * @param iduser {@link Integer}
	 * @param quantite {@link Integer}
	 * @param idArticle {@link Article}
	 * @return panier {@link Panier}
	 */
	public Panier updatePanier(int iduser, int quantite, int idArticle) {

		Panier panier = null;

		panier = hPanierRep.getByUserAndArticle(iduser, idArticle);

		panier.setQuantite(quantite);

		hPanierRep.save(panier);

		return panier; 
	}

	/**
	 * Permet de vérifier si un article est déja present dans la base de données
	 * @param iduser {@link Integer}
	 * @param quantite {@link Integer}
	 * @param iduser {@link Integer}
	 * @return {@link Boolean}
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


	/**
	 * Insere une ligne de panier dans le panier d'un User 
	 * @param p_user {@link Account}
	 * @param p_article {@link Article}
	 * @param quantite {@link Integer}
	 * @return panier {@link Panier}
	 */
	@Override
	public Panier insertArticle(Account p_user, Article p_article, int quantite) {

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
	 * Supprime la liste de panier appartenant à un User
	 * @param idUser {@link Integer}
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
	 * Récupère une liste de Panier appartenant à un User
	 * @param p_id {@link Integer}
	 * @return list panier {@link List} {@link Panier}
	 */
	@Override
	public List<Panier> getListArticle(int p_id) {

		List<Panier> hArticlePanier = hPanierRep.getListPaniers(p_id);

		log.info("OK - List<Panier> getListArticle(int p_user)");

		return hArticlePanier;	
	}

	/**
	 * Sauvegarde la liste de paniers dans la base de données
	 * @param lPanier {@link List} {@link Panier}
	 */
	public void savePanier(List<Panier> lPanier) {
		hPanierRep.saveAll(lPanier);
	}


	/**
	 * Permet de calculer le prix total du panier courant
	 * @param p_id {@link Integer}
	 * @return totalPrix {@link Integer}
	 */
	public Integer totalPanier(int p_id) {

		Integer totalPrice = 0;

		List<Panier> listPaniers = hPanierRep.getListPaniers(p_id);

		for (Panier panier : listPaniers) {
			totalPrice += panier.getArticle().getPrix() * panier.getQuantite();
		}

		log.info("le total est" + totalPrice);

		return totalPrice;
	}


	
	/**
	 * Permet suite à vérification du contenu du panier 
	 * Soit d'ajouter un une ligne de panier
	 * Soit de mettre à jour cette ligne de panier
	 * @param p_lPanier {@link List} {@link Panier}
	 * @param p_user {@link Account}
	 * @param p_article {@link Article}
	 * @param p_quantite {@link Integer}
	 * @return list Panier {@link List} {@link Panier}
	 */
	public List<Panier> addCadie(List<Panier> p_lPanier, Account p_user, Article p_article, int p_quantite){

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
		//cela signifie que l'articl e n'existe pas dans mon panier 
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

	/**
	 * permet de claculer le prix total du panier
	 * @param p_user {@link Account}
	 * @return {@link List} {@link Panier}
	 */
	public List<Panier> setPrixListPanier(Account p_user){

		// Récupération de la liste de panier dans la BDD 
		List<Panier> listePanier = hPanierRep.getListPaniers(p_user.getIdUser());

		// Parcours la liste pour calculer et setter le prix de la ligne 
		for (Panier panier : listePanier) {
			int prixLigne = panier.getArticle().getPrix() * panier.getQuantite();
			panier.setPrix(prixLigne);
		}

		return listePanier;
	}
	
	
	/**
	 * Permet de supprimer un article sauvegarder par un User dans la base de données Panier
	 * @param idArticle {@link Article}
	 * @param p_user {@link Account}
	 */
	public void removeArticle(Integer idArticle, Account p_user) {
		
		Panier hPanier = hPanierRep.getByUserAndArticle(p_user.getIdUser(), idArticle);
		
		hPanierRep.delete(hPanier);
		
	}
	
	/**
	 * Permet de supprimer toutes les lignes de paniers via l'id Article
	 * @param idArticle {@link Article}
	 */
	public void deleteArticle(Integer idArticle) {
		
		List<Panier> lPaniers = hPanierRep.deletePanierByIDArticle(idArticle);
		
		hPanierRep.deleteAll(lPaniers);
	}
}
