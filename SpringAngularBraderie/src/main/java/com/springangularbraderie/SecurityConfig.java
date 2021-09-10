/**
 * 
 */
package com.springangularbraderie;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author JRSS
 * Fichier de configuration
 *
 */

// on indique fichier de configuration spring
// @EnableWebSecurity permet de valider les modifs faite par la classe abstraite WebSecurityConfigurerAdapter
// @EnableGlobalMethodSecurity The prePostEnabled property enables Spring Security pre/post annotations.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
// WebSecurityConfigurerAdapter permet d'overrider les sécurité web avec les notres
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//        @Bean
	//        CorsConfigurationSource corsConfigurationSource() {​​​​​​​
	//            CorsConfiguration config = new CorsConfiguration();
	//            config.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://localhost:9000"));
	//            config.setAllowedMethods(Arrays.asList("GET","POST"));
	//            config.setAllowCredentials(true);
	//            //the below three lines will add the relevant CORS response headers
	//            config.addAllowedOrigin("http://localhost:4200");
	//            config.addAllowedOrigin("http://localhost:9000");
	//            config.addAllowedHeader("*");
	//            config.addAllowedMethod("*");
	//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	//            source.registerCorsConfiguration("/**", config);
	//            return source;
	//        }​​​​​​​

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
		//Authentification en mémoire static
		auth.inMemoryAuthentication().withUser("Admin").password("{​​​​​​​noop}​​​​​​​admin132").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Marleyb").password("{​​​​​​​noop}​​​​​​​marleyb123").roles("USER");
		auth.inMemoryAuthentication().withUser("Charliep").password("{​​​​​​​noop}​​​​​​​charleip123").roles("USER");
		auth.inMemoryAuthentication().withUser("Milesd").password("{​​​​​​​noop}​​​​​​​milesd123").roles("USER");
		auth.inMemoryAuthentication().withUser("Keithj").password("{​​​​​​​noop}​​​​​​​keithj123").roles("USER");

	}

	// on configure nos servcies HTTP en lui passant les méthodes accessibles 
	// (GEt/ post) dans les antMatchers ( qui coreespondent au URl de notre appli ainsi que le role de l'accebilité
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
		//http.cors();
		http
		//HTTP Basic authentication
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/magasin/getAllArticle/**").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/magasin/getListPanier").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/magasin/getArticle/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/magasin/savePanier/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/index/user/**").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/magasin/clear/**").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "caddie/removeArticle/**").hasRole("USER")    
		.and()
		.csrf().disable()
		.formLogin().disable();

		// You can customize the following part based on your project, it's only a sample
		//        http
		//        .authorizeRequests()
		//        .antMatchers("/**")
		//        .permitAll()
		//        .anyRequest()
		//        .authenticated()
		//        .and()
		//        .csrf()
		//        .disable()
		//        .cors()
		//        .configurationSource(request -> corsConfiguration);
	}
}











