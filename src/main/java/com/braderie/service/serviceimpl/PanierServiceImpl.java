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
import com.braderie.repository.PanierRepository;
import com.braderie.service.PanierService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JRSS Manipulation des Paniers
 */
@Service
@Slf4j
public class PanierServiceImpl implements PanierService {
	
	private static final String ARTICLE_INSERT_SUCCESS = "Article inséré avec succès";
	
	private static final String ARTICLE_UPDATE_SUCCESS = "Article mis à jour avec succès";
	
	private static final String ARTICLE_DELETE_SUCCESS = "Article supprimé avec succès";

	@Autowired
	PanierRepository hPanierRep;

	@Autowired
	ArticleServiceImpl hArticleService;

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

	@Override
	public void deleteAll(int idUser) {
		hPanierRep.deleteAll(hPanierRep.getListPaniers(idUser));
		log.info(ARTICLE_DELETE_SUCCESS);
	}

	@Override
	public List<Panier> getListArticle(int idUser) {
		return hPanierRep.getListPaniers(idUser);
	}

	public List<Panier> setPrixListPanier(Account user) {
		List<Panier> listePanier = hPanierRep.getListPaniers(user.getIdUser());
		listePanier.forEach(p -> p.setPrix(p.getArticle().getPrix() * p.getQuantite()));
		return listePanier;
	}

	public void removeArticle(Integer idArticle, Account user) {
		Panier hPanier = hPanierRep.getByUserAndArticle(user.getIdUser(), idArticle);
		hPanierRep.delete(hPanier);
	}
	
	private Panier updatePanier(int iduser, int quantite, int idArticle) {
		Panier panier = hPanierRep.getByUserAndArticle(iduser, idArticle);
		panier.setQuantite(quantite);
		hPanierRep.save(panier);
		return panier;
	}
	
	private boolean verifIdArticleBase(int user, int idArticle) {
		List<Panier> lPanier = new ArrayList<>(hPanierRep.getListPaniers(user));
		return lPanier.stream().anyMatch(s -> s.getArticle().getIdArticle() == idArticle);
	}
}
