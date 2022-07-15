/**
 * Package repository
 */
package com.braderie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.braderie.entity.Article;

/**
 * @author JRSS
 * Repository de gestion des Articles (Admin)
 *
 */
@Repository
public interface AdminRepository extends JpaRepository<Article, Integer> {

}
