package com.rezdy.lunch.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	  @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()               
	            .apis(RequestHandlerSelectors.any())
	                .paths(paths())
	                .build()           
	               .apiInfo(apiInfo());
	  }

	  private ApiInfo apiInfo() {
	      return new ApiInfoBuilder()
	    		  .title("Rezdy API System")
	    		  .description("Some custom description of API.")
	    		  .contact(new Contact("Allan Joseph Evangelista", "https://www.linkedin.com/in/allan-joseph-evangelista-60463545/", "https://github.com/eallanjoseph123"))
	    		  .license("License of API")
	    		  .licenseUrl("https://www.linkedin.com/in/allan-joseph-evangelista-60463545/")
	    		  .build();
	  }
	  
	  private Predicate<String> paths() {
	        return or(
	        		regex("/lunch.*")
	        );
	    }
}
