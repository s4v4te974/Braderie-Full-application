/**
 * Package Application
 */
package com.springangularbraderie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * @author JRSS 
 * permet de configurer swagger 
 *
 */
@Configuration
public class SpringFoxConfig {

	/**
	 * Configuration de swagger 
	 * permet de builder l'interface de swagger
	 * @return un docket + configuration  {@link Docket}
	 */
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("com.springangularbraderie"))              
				.paths(PathSelectors.any())                          
				.build()
				.apiInfo(apiInfo());                                           
	}

	/**
	 * Configuration de swagger 
	 * permet de remplir les informations de l'interface swagger
	 * @return un docket + configuration  {@link ApiInfoBuilder}
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("JRSS Braderie Boot REST API documentation")
				.description("REST APIs For Managing Article, cart in Braderie")
				.contact(new Contact("Team JRSS", "Les CDA7 en puissance!!!!!", "TMTC"))
				.version("1.0")
				.build();
	}
}