/**
 * Package repository
 */
package com.springangularbraderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangularbraderie.entity.Article;

/**
 * @author JRSS
 * Repository de gestion des Articles (Admin)
 *
 */
@Repository
public interface IAdminRep extends JpaRepository<Article, Integer> {

}
