/**
 * 
 */
package com.springangularbraderie.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.springangularbraderie.model.Account;
import com.springangularbraderie.repository.IUserRep;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 31010-69-04
 *
 */
@Service
@Slf4j
public class AuthentificationService implements UserDetailsService {

	@Autowired
	private IUserRep userRep;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@SuppressWarnings("unused")
	@Override
	public User loadUserByUsername(String login) throws UsernameNotFoundException {

		// on recupere le user par son nom
		
		log.info("le login est : " + login);

		Account hAccount = userRep.findOneByLogin("Marleyb");
		
		log.info(" nous sommes dans l'authentification ");

		log.info(hAccount.toString());

		if(hAccount == null) {
			throw new UsernameNotFoundException("Utilisateur non trouvé : " + login);
		}

		User hUser = createUser(hAccount);
		
		log.info("user créer ! ");

		return hUser;

	}

	// on utilise user de srping Security ( ça doit offrir plus de protection)
	private User createUser(Account p_account) {
		return new User(p_account.getLogin(), passwordEncoder.encode(p_account.getPass()), createAuthorities(p_account));
	}

	
	private Collection<GrantedAuthority> createAuthorities(Account login){
		Collection<GrantedAuthority> authorities = new ArrayList<>();	
		authorities.add(new SimpleGrantedAuthority("ROLE_" + login.getRole()));
		
		for (GrantedAuthority grantedAuthority : authorities) {
			log.info(" grantedAuthority " + grantedAuthority);
		}
		
		return authorities;
	}
}
