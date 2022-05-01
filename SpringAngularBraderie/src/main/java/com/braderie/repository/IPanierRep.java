/**
 * Package repository
 */
package com.braderie.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.braderie.entity.Panier;

/**
 * @author JRSS
 * Repository de gestion du panier
 */

@Repository
public interface IPanierRep extends JpaRepository<Panier, Integer> {
	
	/**
	 * Permet de recuperer la liste Panier dans la Base de données
	 * @param user {@link Integer}
	 * @return listePanier {@link List} {@link Panier}
	 */
	@Query("select p from Panier p where p.user.idUser= :idUser")
	List<Panier> getListPaniers(@Param("idUser") Integer idUser);
	
	/**
	 * Permet de recuperer un panier par l'iduser et idArticle
	 * @param iduser {@link Integer}
	 * @param idArticle {@link Integer}
	 * @return panier {@link Panier}
	 */
	@Query("select p from Panier p where p.user.idUser= :idUser and p.article.idArticle= :idArticle")
	Panier getByUserAndArticle(@Param("idUser")Integer iduser, @Param("idArticle")Integer idArticle) ;
	
	/**
	 * Permet de supprimer une ligne de Panier l'idArticle
	 * @param idArticle {@link Integer}
	 * @return listePanier {@link List} {@link Panier}
	 */
	@Query("select p from Panier p where p.article.idArticle= :idArticle")
	List<Panier> deletePanierByIDArticle(@Param("idArticle")Integer idArticle);
	
}
