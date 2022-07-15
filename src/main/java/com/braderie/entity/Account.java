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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    
    @Column(nullable = false, length = 10)
    private String login;
    
    @Column(nullable = false, length = 20)
    private String pass;
    
    @Column(nullable = true, length = 100)
    private int nbConnexion;
    
    @Column(nullable = false, length = 10)
    private String role;
    
}
