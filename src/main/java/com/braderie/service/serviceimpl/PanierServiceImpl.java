/**
 * Package Service
 */
package com.braderie.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braderie.entity.Account;
import com.braderie.entity.Article;
import com.braderie.entity.Panier;
import com.braderie.repository.IPanierRep;
import com.braderie.service.IPanierService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS Manipulation des Paniers
 */
@Service
@Slf4j
public class PanierServiceImpl implements IPanierService {
	
	private static final String ARTICLE_INSERT_SUCCESS = "Article inséré avec succès";
	
	private static final String ARTICLE_UPDATE_SUCCESS = "Article mis à jour avec succès";
	
	private static final String ARTICLE_DELETE_SUCCESS = "Article supprimé avec succès";

	@Autowired
	IPanierRep hPanierRep;

	@Autowired
	ArticleServiceImpl hArticleService;

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
		return lPanier.stream().anyMatch(s -> s.getArticle().getIdArticle() == idArticle);
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

		Panier hPanierUpdate = null;

		boolean verifArticleBDD = verifIdArticleBase(user.getIdUser(), article.getIdArticle());

		if (!verifArticleBDD) {

			Panier hPanier = Panier.builder() //
					.user(user) //
					.article(article) //
					.quantite(quantite) //
					.build();

			hPanierUpdate = hPanierRep.save(hPanier);
			
			log.info("idpanier" + hPanier.getIdPanier());

			log.info(ARTICLE_INSERT_SUCCESS);

		} else {

			int quantiteUpdate = hPanierRep.getByUserAndArticle(user.getIdUser(), article.getIdArticle()).getQuantite() + quantite;

			hPanierUpdate = updatePanier(user.getIdUser(), quantiteUpdate, article.getIdArticle());

			log.info(ARTICLE_UPDATE_SUCCESS);
		}
		return hPanierUpdate;
	}

	/**
	 * Supprime la liste de panier appartenant à un User
	 * 
	 * @param idUser {@link Integer}
	 */
	@Override
	public void deleteAll(int idUser) {
		hPanierRep.deleteAll(hPanierRep.getListPaniers(idUser));
		log.info(ARTICLE_DELETE_SUCCESS);
	}

	/**
	 * Récupère une liste de Panier appartenant à un User
	 *  
	 * @param p_id {@link Integer}
	 * @return list panier {@link List} {@link Panier}
	 */
	@Override
	public List<Panier> getListArticle(int idUser) {
		return hPanierRep.getListPaniers(idUser);
	}

	/**
	 * permet de calculer le prix total du panier
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
	 * Met à jour la quantité d'une ligne de panier
	 * 
	 * @param iduser    {@link Integer}
	 * @param quantite  {@link Integer}
	 * @param idArticle {@link Article}
	 * @return panier   {@link Panier}
	 */
	private Panier updatePanier(int iduser, int quantite, int idArticle) {
		Panier panier = hPanierRep.getByUserAndArticle(iduser, idArticle);
		panier.setQuantite(quantite);
		hPanierRep.save(panier);
		return panier;
	}
}
