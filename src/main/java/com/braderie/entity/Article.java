/**
 * Package bean
 */
package com.braderie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JRSS 
 * Fichier Bean Article 
 */

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_Article_SPRING")
public class Article implements Serializable {
    
    private static final long serialVersionUID = -6070679086343208662L;

    /**
     * identifiant Article {@link Integer}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticle;
    
    /**
     * Description de l'article {@link String}
     */
     @Column(nullable = false, length = 20)
    private String description;
    
     /**
      * Marque de l'article {@link String}
      */
     @Column(nullable = false, length = 15)
    private String marque;
    
     /**
      * prix de l'article {@link Integer}
      */
     @Column(nullable = false, length = 5)
    private int prix;
}
