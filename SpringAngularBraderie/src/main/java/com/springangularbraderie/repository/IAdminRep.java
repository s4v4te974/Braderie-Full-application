/**
 * Package repository
 */
package com.springangularbraderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.model.Article;

/**
 * 
 * Classe permettant de communiquer avec la base de données en tant qu'admin
 *
 */
@Repository
public interface IAdminRep extends JpaRepository<Article, Integer> {

}
