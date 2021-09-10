/**
 * Package repository
 */
package com.springangularbraderie.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.model.Panier;

/**
 * @author JRSS
 * Repository de gestion du panier
 */

@Repository
public interface IPanierRep extends JpaRepository<Panier, Integer> {
	
	/**
	 * Permet de recuperer la liste Panier dans la Base de données
	 * @param IdUser {@link Integer}
	 * @return listePanier {@link List} {@link Panier}
	 */
	// Methode qui retourne la liste deu Panier ( Article + colonne Article) en fonction de l'user passer en paramètre
	@Query("select p from Panier p where p.User.idUser= :idUser")
	List<Panier> getListPaniers(@Param("idUser") Integer IdUser);
	
	// Methode qui retourne une ligne de panier en fonction de l'user et de l'article ( utilisé pour l'update)
	/**
	 * Permet de recuperer un panier par l'iduser et idArticle
	 * @param iduser {@link Integer}
	 * @param idArticle {@link Integer}
	 * @return panier {@link Panier}
	 */
	@Query("select p from Panier p where p.User.idUser= :idUser and p.Article.idArticle= :idArticle")
	Panier getByUserAndArticle(@Param("idUser")Integer iduser, @Param("idArticle")Integer idArticle) ;
	
	
	@Query("select p from Panier p where p.Article.idArticle= :idArticle")
	List<Panier> deletePanierByIDArticle(@Param("idArticle")Integer idArticle);
	
}
