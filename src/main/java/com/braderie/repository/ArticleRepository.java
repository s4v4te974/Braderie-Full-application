/**
 * Package repository
 */
package com.braderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.braderie.entity.Article;


/**
 * @author JRSS
 *	Repository de gestion des Articles base de données
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}