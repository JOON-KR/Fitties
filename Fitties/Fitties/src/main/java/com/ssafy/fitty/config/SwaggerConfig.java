package com.ssafy.fitty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	  @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Spring Board REST API")
	              .description("게시판 실습 REST API입니다.")
	              .version("v0.0.1")
	              .license(new License().name("SSAFY").url("http://ssafy.com")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SpringShop Wiki Documentation")
	              .url("https://springshop.wiki.github.org/docs"));
	  }
}
