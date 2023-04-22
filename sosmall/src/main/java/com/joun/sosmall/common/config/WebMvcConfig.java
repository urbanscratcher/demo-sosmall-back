package com.joun.sosmall.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.joun.sosmall.common.interceptor.CheckUserTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private CheckUserTokenInterceptor userTokenInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    // 토큰확인
    registry.addInterceptor(userTokenInterceptor).addPathPatterns("/api/members/**",
        "/api/bank-accounts/**",
        "/api/addresses/**",
        "/api/coupons/**",
        "/api/orders/**",
        "/api/carts/**",
        "/api/reviews/**",
        "/api/interests/**");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true).allowedMethods("*");
  }

}
