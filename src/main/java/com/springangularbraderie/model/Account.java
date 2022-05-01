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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JRSS 
 * Fichier Bean utilisateur
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="T_User_SPRING")
public class Account implements Serializable {
    
    private static final long serialVersionUID = -6417274757131465118L;
    
    /**
     * identifiant User {@link Integer}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    
    /**
     * Login Utilisateur {@link String}
     */
    @Column(nullable = false, length = 10)
    private String login;
    
    /**
     * Mot de passe Utilisateur {@link String}
     */
    @Column(nullable = false, length = 20)
    private String pass;
    
    /**
     * Nombre de connexion de l'utilisateur {@link Integer}
     */
    @Column(nullable = true, length = 100)
    private int nbConnexion;
    
    /**
     * Role de l'utilisateur 
     * permet de determiner son niveau d'accessibilité à l'application {@link String}
     */
    @Column(nullable = false, length = 10)
    private String role;
    
}
