/**
 * Package Application
 */
package com.springangularbraderie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author JRSS
 * classe de lancement de l'application
 *
 */
@SpringBootApplication
@EnableSwagger2
public class SpringAngularBraderieApplication {

	/**
	 * Cette methode permet de lancer l'application
	 * @param args {@link String}
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringAngularBraderieApplication.class, args);
	}

}
