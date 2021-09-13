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
     * @param p_idAccount {@link Integer}
     * @param p_login {@link String}
     * @param p_pass {@link String}
     * @param p_nbConnexion {@link Integer}
     * @param p_role {@link String}
     */
    public Account(int p_idAccount, String p_login, String p_pass, int p_nbConnexion, String p_role) {
        this.idUser = p_idAccount;
        this.login = p_login;
        this.pass = p_pass;
        this.nbConnexion = p_nbConnexion;
        this.role = p_role;
    }
}
