/**
 * 
 */
package com.springangularbraderie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springangularbraderie.service.AuthentificationService;

/**
 * @author 31010-69-04
 *
 */

// on indique fichier de configuration spring
// @EnableWebSecurity permet de valider les modifs faite par la classe abstraite WebSecurityConfigurerAdapter
// @EnableGlobalMethodSecurity The prePostEnabled property enables Spring Security pre/post annotations.

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// WebSecurityConfigurerAdapter permet d'overrider les sécurité web avec les notres
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthentificationService hAuthentificationService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();		
	}

	// on configure nos servcies HTTP en lui passant les méthodes accessibles 
	// (GEt/ post) dans les antMatchers ( qui coreespondent au URl de notre appli ainsi que le role de l'accebilité
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/SpringAngularBraderie/**").hasRole("admin")
		.antMatchers(HttpMethod.GET, "/magasin/getAllArticle/**").hasRole("user")
		.antMatchers(HttpMethod.GET, "/magasin/getListPanier").hasRole("user")
		.antMatchers(HttpMethod.GET, "/magasin/getArticle/**").hasRole("user")
		.antMatchers(HttpMethod.POST, "/magasin/savePanier/**").hasRole("user")
		.antMatchers(HttpMethod.POST, "/index/user/**").hasRole("user")
		.antMatchers(HttpMethod.DELETE, "/magasin/clear/**").hasRole("user")
		.antMatchers(HttpMethod.DELETE, "caddie/removeArticle/**").hasRole("user")	
		.and().httpBasic();

		http.csrf().disable().cors().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(true).userDetailsService(hAuthentificationService).passwordEncoder(passwordEncoder());
	}
}
