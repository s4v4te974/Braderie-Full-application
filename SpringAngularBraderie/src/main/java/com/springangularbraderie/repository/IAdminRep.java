/**
 * 
 */
package com.springangularbraderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.model.Article;

/**
 * @author Stephane Kouotze CDA7
 *
 */
@Repository
public interface IAdminRep extends JpaRepository<Article, Integer> {

}
