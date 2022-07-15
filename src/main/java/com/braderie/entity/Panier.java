/**
 * Package bean
 */
package com.braderie.entity;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JRSS
 * Fichier Bean Panier
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_Panier_SPRING")
public class Panier implements Serializable {
    
    private static final long serialVersionUID = 2608804565680180566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPanier;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(name="idUser", referencedColumnName = "idUser", nullable = false)
    private Account user; 
    
    @ManyToOne(targetEntity = Article.class, fetch = FetchType.EAGER)
    @JoinColumn(name="idArticle", referencedColumnName = "idArticle", nullable=false)
    private Article article;
    
    @Column(nullable = false, length = 5)
    private int quantite;
    
    @Transient
    private int prix;
}
