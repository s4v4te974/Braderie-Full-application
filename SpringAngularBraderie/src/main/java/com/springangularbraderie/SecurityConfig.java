/**
 * Package Application
 */
package com.springangularbraderie;

import java.util.Arrays;
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


/**
 * @author JRSS
 * Fichier de configuration HTTP
 *
 */

// on indique fichier de configuration spring
// @EnableWebSecurity permet de valider les modifs faite par la classe abstraite WebSecurityConfigurerAdapter
// @EnableGlobalMethodSecurity The prePostEnabled property enables Spring Security pre/post annotations.
//WebSecurityConfigurerAdapter permet d'overrider les sécurité web avec les notres
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/**
	 * Configuration Globale
	 * @param auth {@link AuthenticationManagerBuilder}
	 */	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth)  {

		try {
			//Authentification en mémoire static

			auth.inMemoryAuthentication().withUser("Admin").password(passwordEncoder().encode("admin132")).roles("ADMIN");
			auth.inMemoryAuthentication().withUser("Marleyb").password(passwordEncoder().encode("marleyb123")).roles("USER");
			auth.inMemoryAuthentication().withUser("Charliep").password(passwordEncoder().encode("charleip123")).roles("USER");
			auth.inMemoryAuthentication().withUser("Milesd").password(passwordEncoder().encode("​​​​​​​milesd123")).roles("USER");
			auth.inMemoryAuthentication().withUser("Keithj").password(passwordEncoder().encode("​​​​​​​keithj123")).roles("USER");

			//auth.inMemoryAuthentication().withUser("Admin").password("​​​​​​​{noop}admin132").roles("ADMIN");
			//			auth.inMemoryAuthentication().withUser("Marleyb").password("{noop}marleyb123").roles("USER");
			//			auth.inMemoryAuthentication().withUser("Charliep").password("{​​​​​​​noop}​​​​​​​charleip123").roles("USER");
			//			auth.inMemoryAuthentication().withUser("Milesd").password("{​​​​​​​noop}​​​​​​​milesd123").roles("USER");
			//			auth.inMemoryAuthentication().withUser("Keithj").password("{​​​​​​​noop}​​​​​​​keithj123").roles("USER");
		}catch (Exception e) {
			e.getMessage();
		}
	}

	// on configure nos servcies HTTP en lui passant les méthodes accessibles 
	// (GEt/ post) dans les antMatchers ( qui coreespondent au URl de notre appli ainsi que le role de l'accebilité

	/**
	 * Configuration des accès Http de l'application
	 * ainsi que le routing de l'application
	 * @param http {@link HttpSecurity}
	 */	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Instanciation d'un cors config
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
		/**
		 * Sécurisation des APIRest:
		 * pour les rôles admin pas de restrictions
		 * pour les rôles USER restriction sur les PUT, PATCH et DELETE
		 */

		http
		//HTTP Basic authentication
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/magasin/getAllArticle/**").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.GET, "/magasin/getListPanier").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.GET, "/magasin/getArticle/**").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "/magasin/savePanier/**").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "/index/user/**").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.DELETE, "/magasin/clear/**").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.DELETE, "/caddie/removeArticle/**").hasAnyRole("USER","ADMIN")  
		.antMatchers(HttpMethod.POST, "/Admin/createArticle/**").hasRole("ADMIN") 
		.antMatchers(HttpMethod.DELETE, "/Admin/removeAdmin/**").hasRole("ADMIN") 
		.antMatchers(HttpMethod.PUT, "/Admin/updateArticle/**").hasRole("ADMIN") 
		.and()
		.csrf().disable()
		.formLogin().disable();

	}
}
