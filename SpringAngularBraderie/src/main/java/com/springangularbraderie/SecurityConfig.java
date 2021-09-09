/**
 * 
 */
package com.springangularbraderie;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.springangularbraderie.service.AuthentificationService;

import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

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
@Slf4j
// WebSecurityConfigurerAdapter permet d'overrider les sécurité web avec les notres
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthentificationService hAuthentificationService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();		
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200","https://localhost:9000"));
        config.setAllowedMethods(Arrays.asList("GET","POST"));
        config.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("http://localhost:9000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

	// on configure nos servcies HTTP en lui passant les méthodes accessibles 
	// (GEt/ post) dans les antMatchers ( qui coreespondent au URl de notre appli ainsi que le role de l'accebilité
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("methode configure");
		http.authorizeRequests()
		.and()
		.formLogin()
		.loginProcessingUrl("/index/user")
		.usernameParameter("login")
        .passwordParameter("pass")
		.defaultSuccessUrl("/magasin/**", true).permitAll()
		.failureUrl("/robert.html?error=true")
//		.antMatchers("/SpringAngularBraderie/**").hasRole("admin")
//		.antMatchers(HttpMethod.GET, "/magasin/getAllArticle/**").hasRole("user")
//		.antMatchers(HttpMethod.GET, "/magasin/getListPanier").hasRole("user")
//		.antMatchers(HttpMethod.GET, "/magasin/getArticle/**").hasRole("user")
//		.antMatchers(HttpMethod.POST, "/magasin/savePanier/**").hasRole("user")
//		.antMatchers(HttpMethod.POST, "/index/user/**").hasRole("user")
//		.antMatchers(HttpMethod.DELETE, "/magasin/clear/**").hasRole("user")
//		.antMatchers(HttpMethod.DELETE, "caddie/removeArticle/**").hasRole("user")	
		.and().httpBasic();

		http.csrf().disable().cors().configurationSource(corsConfigurationSource());
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(true).userDetailsService(hAuthentificationService).passwordEncoder(passwordEncoder());
	}
}
