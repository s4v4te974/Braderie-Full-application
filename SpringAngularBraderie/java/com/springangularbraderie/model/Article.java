/**
 * Package bean
 */
package com.springangularbraderie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author JRSS 
 * Fichier Bean Article 
 */

@Data
@Entity
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
    
     /**
      * Constructeur Basique de la classe {@link Article}
      */
    public Article() { 
    }

    /**
     * Permet de construire un Article avec tous ses Attributs
     * @param idArticle {@link Integer}
     * @param description {@link String}
     * @param marque {@link String}
     * @param prix {@link Integer}
     */
    public Article(int idArticle, String description, String marque, int prix) {
        this.idArticle = idArticle;
        this.description = description;
        this.marque = marque;
        this.prix = prix;
        
    }

    /**
     * Permet de construire un Article spécifique
     * @param description {@link String}
     * @param marque {@link String}
     * @param prix {@link Integer}
     */
    public Article(String description, String marque, int prix) {
        this.description = description;
        this.prix = prix;
        this.marque = marque;        
    }
}
