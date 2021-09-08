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
 * @author kingd
 *
 */

@Data
@Entity
@Table(name="T_User_SPRING")
public class Account implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6417274757131465118L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    
    @Column(nullable = false, length = 10)
    private String login;
    
    @Column(nullable = false, length = 20)
    private String pass;
    
    @Column(nullable = false, length = 100)
    private int nbConnexion;
    
    // ajout Spring Security
    @Column(nullable = false, length = 10)
    private String role;
    
    /**
     * 
     */
    public Account() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param idUser
     * @param nomUser
     * @param pass
     * @param nbConnexion
     */
    public Account(int p_idUser, String p_login, String p_pass, int p_nbConnexion) {
        this.idUser = p_idUser;
        this.login = p_login;
        this.pass = p_pass;
        this.nbConnexion = p_nbConnexion;
    }
}
