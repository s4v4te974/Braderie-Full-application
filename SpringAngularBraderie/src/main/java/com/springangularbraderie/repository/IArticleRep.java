/**
 * Package repository
 */
package com.springangularbraderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.model.Article;


/**
 * @author JRSS
 *	Repository de gestion des Articles
 *
 */
@Repository
public interface IArticleRep extends JpaRepository<Article, Integer> {

}
