package com.springangularbraderie.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


 

/**
 * @author kingd
 *
 */

@Data
@Entity
@Table(name = "T_Article_SPRING")
public class Article implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6070679086343208662L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticle;
    
//     @Column(nullable = false, length = 20)
    private String description;
    
//     @Column(nullable = false, length = 15)
    private String marque;
    
//     @Column(nullable = false, length = 5)
    private int prix;
    
    /**
     * 
     */
    public Article() {
        
    }

    /**
     * @param idArticle
     * @param description
     * @param marque
     * @param prix
     */
    public Article(int p_idArticle, String p_description, String p_marque, int p_prix) {
        this.idArticle = p_idArticle;
        this.description = p_description;
        this.marque = p_marque;
        this.prix = p_prix;
        
    }

    public Article(String p_description, String p_marque, int p_prix) {
        this.description = p_description;
        this.prix = p_prix;
        this.marque = p_marque;        

    }
    
    public Article(String p_description, String p_marque, int p_prix, int p_idPanier, int p_idArticle) {
        this.description = p_description;
        this.prix = p_prix;
        this.marque = p_marque;                
        this.idArticle = p_idArticle;    
    }

    @Override
    public String toString() {
        return "Article [idArticle=" + idArticle + ", Description=" + description + ", Marque=" + marque + ", Prix="
                + prix + "]";
    }
}



