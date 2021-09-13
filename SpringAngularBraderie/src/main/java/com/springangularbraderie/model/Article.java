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
     * @param p_idArticle {@link Integer}
     * @param p_description {@link String}
     * @param p_marque {@link String}
     * @param p_prix {@link Integer}
     */
    public Article(int p_idArticle, String p_description, String p_marque, int p_prix) {
        this.idArticle = p_idArticle;
        this.description = p_description;
        this.marque = p_marque;
        this.prix = p_prix;
        
    }

    /**
     * Permet de construire un Article spécifique
     * @param p_description {@link String}
     * @param p_marque {@link String}
     * @param p_prix {@link Integer}
     */
    public Article(String p_description, String p_marque, int p_prix) {
        this.description = p_description;
        this.prix = p_prix;
        this.marque = p_marque;        
    }
}
