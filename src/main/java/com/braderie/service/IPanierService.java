/**
 * Package Service
 */
package com.braderie.service;

import java.util.List;
import java.util.Optional;

import com.braderie.entity.Account;
import com.braderie.entity.Article;
import com.braderie.entity.Panier;

/**
 * @author JRSS
 * Interface de panierService
 */

public interface IPanierService {
	
	/**
	 * permet d'inserer un article dans la base de données
	 * @param user {@link Account}
	 * @param article {@link Article}
	 * @param quantite {@link Integer}
	 * @return panier {@link Panier}
	 */
	public Panier insertArticle (Account user, Article article, int quantite);
	
	/**
	 * Supprime la liste de panier appartenant à un User
	 * @param idUser {@link Integer}
	 */
	public void deleteAll (int idUser);

	/**
	 * Récupère une liste de Panier appartenant à un User
	 * @param id {@link Integer}
	 * @return Liste Panier {@link Optional} {@link Panier}
	 */
	public List<Panier> getListArticle(int id);


}
