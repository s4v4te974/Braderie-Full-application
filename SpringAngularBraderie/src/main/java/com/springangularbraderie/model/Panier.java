package com.springangularbraderie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * @author sandrine
 *
 */

@Data
@Entity
@Table(name = "T_Panier_SPRING")
public class Panier implements Serializable {
    
    /**
     * 
     */
	
    private static final long serialVersionUID = 2608804565680180566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPanier;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="idUser", referencedColumnName = "idUser", nullable = false)
    private User User; 
    
    @ManyToOne(targetEntity = Article.class, fetch = FetchType.EAGER)
    @JoinColumn(name="idArticle", referencedColumnName = "idArticle", nullable=false)
    private Article Article;
    
    @Column(nullable = false, length = 5)
    private int quantite;
    
    @Transient
    private int prix;
    
    public Panier() {
        
    }

 

    /**
     * @param idUser
     * @param idArticle
     * @param quantite
     */
    public Panier(User p_User, Article p_Article, int quantite) {
        this.User = p_User;
        this.Article = p_Article;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier [idPanier=" + idPanier + ", User=" + User + ", Article=" + Article + ", quantite="
                + quantite + "]";
    }

}
