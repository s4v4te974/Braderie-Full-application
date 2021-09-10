/**
 * Package Service
 */
package com.springangularbraderie.service;

import java.util.List;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.Account;

/**
 * @author JRSS
 * Interface de panierService
 */

public interface IPanierService {
	
	/**
	 * permet d'inserer un article dans la base de données
	 * @param p_user {@link Account}
	 * @param p_article {@link Article}
	 * @param quantite {@link Integer}
	 * @return panier {@link Panier}
	 */
	public Panier insertArticle (Account p_user, Article p_article, int quantite);
	
	/**
	 * Supprime la liste de panier appartenant à un User
	 * @param idUser {@link Integer}
	 */
	public void deleteAll (int idUser);

	/**
	 * Récupère une liste de Panier appartenant à un User
	 * @param idUser {@link Integer}
	 */
	public List<Panier> getListArticle(int p_id);


}
