/**
 * Package Application
 */
package com.braderie;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author JRSS
 * classe de lancement de l'application
 *
 */
@SpringBootApplication
public class SpringAngularBraderieApplication {
	
	
	@Bean
	public ErrorViewResolver customErrorViewResolver() {
	    final ModelAndView redirectToIndexHtml = new ModelAndView("forward:/index.html", Collections.emptyMap(), HttpStatus.OK);
	    return (request, status, model) -> status == HttpStatus.NOT_FOUND ? redirectToIndexHtml : null;
	}

	/**
	 * Cette methode permet de lancer l'application
	 * @param args {@link String}
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringAngularBraderieApplication.class, args);
	}

}
