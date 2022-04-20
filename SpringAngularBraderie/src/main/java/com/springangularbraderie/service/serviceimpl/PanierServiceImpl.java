/**
 * Package Service
 */
package com.springangularbraderie.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IPanierRep;
import com.springangularbraderie.service.IPanierService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS Manipulation des Paniers
 */
@Data
@Service
@Slf4j
public class PanierServiceImpl implements IPanierService {

	private Integer prixLigne = 0;

	@Autowired
	IPanierRep hPanierRep;

	@Autowired
	ArticleServiceImpl hArticleService;

	/**
	 * Met à jour la quantité d'une ligne de panier
	 * 
	 * @param iduser    {@link Integer}
	 * @param quantite  {@link Integer}
	 * @param idArticle {@link Article}
	 * @return panier   {@link Panier}
	 */
	public Panier updatePanier(int iduser, int quantite, int idArticle) {
		Panier panier = hPanierRep.getByUserAndArticle(iduser, idArticle);
		panier.setQuantite(quantite);
		hPanierRep.save(panier);
		return panier;
	}

	/**
	 * Permet de vérifier si un article est déja present dans la base de données
	 * 
	 * @param iduser   {@link Integer}
	 * @param quantite {@link Integer}
	 * @param iduser   {@link Integer}
	 * @return         {@link Boolean}
	 */

	private boolean verifIdArticleBase(int user, int idArticle) {
		List<Panier> lPanier = new ArrayList<>(hPanierRep.getListPaniers(user));
		Boolean alreadyExist = lPanier.stream().anyMatch(s -> s.getArticle().getIdArticle() == idArticle);
		log.info("OK - verifIdArticleBase");
		return alreadyExist;
	}

	/**
	 * Insère une ligne de panier dans le panier d'un User
	 * 
	 * @param user     {@link Account}
	 * @param article  {@link Article}
	 * @param quantite {@link Integer}
	 * @return panier {@link Panier}
	 */
	@Override
	public Panier insertArticle(Account user, Article article, int quantite) {
		
		log.info("current user" + user.getLogin());

		Panier hPanierAdded = null;

		boolean verifArticleBDD = verifIdArticleBase(user.getIdUser(), article.getIdArticle());

		if (!verifArticleBDD) {

			Panier hPanier = Panier.builder() //
					.User(user) //
					.Article(article) //
					.quantite(quantite) //
					.build();

			hPanierAdded = hPanierRep.save(hPanier);

			log.info("OK - article inséré avec succès");

		} else {

			int quantiteUpdate = hPanierRep.getByUserAndArticle(user.getIdUser(), article.getIdArticle()).getQuantite() + quantite;

			hPanierAdded = updatePanier(user.getIdUser(), quantiteUpdate, article.getIdArticle());

			log.info("OK - Article mis à jour avec succès");
		}
		return hPanierAdded;
	}

	/**
	 * Supprime la liste de panier appartenant à un User
	 * 
	 * @param idUser {@link Integer}
	 */
	@Override
	public void deleteAll(int idUser) {
		hPanierRep.deleteAll(hPanierRep.getListPaniers(idUser));
		log.info("Article supprimé avec succès");
	}

	/**
	 * Récupère une liste de Panier appartenant à un User
	 * 
	 * @param p_id {@link Integer}
	 * @return list panier {@link List} {@link Panier}
	 */
	@Override
	public List<Panier> getListArticle(int id) {
		return hPanierRep.getListPaniers(id);
	}

	/**
	 * Sauvegarde la liste de paniers dans la base de données
	 * 
	 * @param lPanier {@link List} {@link Panier}
	 */
	public void savePanier(List<Panier> lPanier) {
		hPanierRep.saveAll(lPanier);
	}

	/**
	 * Permet de calculer le prix total du panier courant
	 * 
	 * @param p_id {@link Integer}
	 * @return totalPrix {@link Integer}
	 */
	public Integer totalPanier(int id) {
		List<Panier> listPaniers = hPanierRep.getListPaniers(id);
		int totalPrice = listPaniers.stream().mapToInt(p -> p.getArticle().getPrix() * p.getQuantite()).sum();
		log.info("le total est" + totalPrice);
		return totalPrice;
	}

	/**
	 * Permet suite à vérification du contenu du panier Soit d'ajouter un une ligne
	 * de panier Soit de mettre à jour cette ligne de panier
	 * 
	 * @param p_lPanier  {@link List} {@link Panier}
	 * @param p_user     {@link Account}
	 * @param p_article  {@link Article}
	 * @param p_quantite {@link Integer}
	 * @return list Panier {@link List} {@link Panier}
	 */
	public List<Panier> addCadie(List<Panier> lPanier, Account user, Article article, int quantite) {

		boolean bIdTrouve = false;

		List<Panier> lPanierLocal = lPanier;

		Integer idArticle = article.getIdArticle();

		for (int i = 0; i < lPanierLocal.size(); i++) {

			if (lPanierLocal.get(i).getArticle().getIdArticle() == idArticle) {

				Integer quantiteUpdate = lPanierLocal.get(i).getQuantite() + quantite;
				lPanierLocal.get(i).setQuantite(quantiteUpdate);

				prixLigne = lPanierLocal.get(i).getArticle().getPrix() * lPanierLocal.get(i).getQuantite();
				lPanierLocal.get(i).setPrix(prixLigne);

				log.info("quantité et prix de la ligne de panier mis à jour : " + lPanier.get(i).toString());

				bIdTrouve = true;
			}
		}

		if (!bIdTrouve) {

			Panier newPanier = Panier.builder() //
					.User(user) //
					.Article(article) //
					.quantite(quantite) //
					.build();

			lPanierLocal.add(newPanier);

			prixLigne = newPanier.getArticle().getPrix() * newPanier.getQuantite();
			newPanier.setPrix(prixLigne);

			log.info("ajout nouvel article dans la liste : " + newPanier.toString());
		}
		return lPanier;
	}

	/**
	 * permet de claculer le prix total du panier
	 * 
	 * @param p_user {@link Account}
	 * @return {@link List} {@link Panier}
	 */
	public List<Panier> setPrixListPanier(Account user) {
		List<Panier> listePanier = hPanierRep.getListPaniers(user.getIdUser());
		listePanier.forEach(p -> p.setPrix(p.getArticle().getPrix() * p.getQuantite()));
		return listePanier;
	}

	/**
	 * Permet de supprimer un article sauvegarder par un User dans la base de
	 * données Panier
	 * 
	 * @param idArticle {@link Article}
	 * @param p_user    {@link Account}
	 */
	public void removeArticle(Integer idArticle, Account user) {
		Panier hPanier = hPanierRep.getByUserAndArticle(user.getIdUser(), idArticle);
		hPanierRep.delete(hPanier);
	}

	/**
	 * Permet de supprimer toutes les lignes de paniers via l'id Article
	 * 
	 * @param idArticle {@link Article}
	 */
	public void deleteArticle(Integer idArticle) {
		List<Panier> lPaniers = hPanierRep.deletePanierByIDArticle(idArticle);
		hPanierRep.deleteAll(lPaniers);
	}
}
