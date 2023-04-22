package com.joun.sosmall.common.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class Swagger2Config {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("v1-sosmall")
        .pathsToMatch("/api/**")
        .build();
  }

  public OpenAPI SpringShopOpenApi() {
    return new OpenAPI()
        .info(new Info()
            .title("SOS mall API")
            .description("SOS 몰 API 명세서")
            .version("v0.0.1")
            .contact(new Contact()
                .name("Hyunjung Joun")
                .email("urbanscratcher@gmail.com")));
  }

  SecurityScheme bearerAuth = new SecurityScheme()
      .type(SecurityScheme.Type.HTTP)
      .scheme("bearer")
      .bearerFormat("JWT")
      .in(SecurityScheme.In.HEADER)
      .name(HttpHeaders.AUTHORIZATION);

}
