/**
 * 
 */
package com.springangularbraderie.service;

import java.util.List;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.model.Panier;
import com.springangularbraderie.model.Account;

/**
 * @author dangs
 *
 */
public interface IPanierService {
	
	public Panier insertArticle (Account p_user, Article p_article, int quantite);
	
	public void deleteAll (int idUser);

	public List<Panier> getListArticle(int p_id);


}
