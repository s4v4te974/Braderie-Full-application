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
 * Fichier Bean utilisateur
 */

@Data
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
    @Column(nullable = false, length = 100)
    private int nbConnexion;
    
    /**
     * Role de l'utilisateur 
     * permet de determiner son niveau d'accessibilité à l'application {@link String}
     */
    @Column(nullable = false, length = 10)
    private String role;
    
    /**
     * Constructeur Basique de la classe {@link Account}
     */
    public Account() {

    }
    
    /**
     * Permet de construire un compte utilisateur avec tous les attributs
     * @param idAccount {@link Integer}
     * @param login {@link String}
     * @param pass {@link String}
     * @param nbConnexion {@link Integer}
     * @param role {@link String}
     */
    public Account(int idAccount, String login, String pass, int nbConnexion, String role) {
        this.idUser = idAccount;
        this.login = login;
        this.pass = pass;
        this.nbConnexion = nbConnexion;
        this.role = role;
    }
}
