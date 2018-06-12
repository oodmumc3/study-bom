package com.crdev.bom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 웹 MVC 설정
 */
@Configuration // -> 설정파일로써 어플리케이션 컨텍스트에 빈 등록을 진행하게됨
@ComponentScan(basePackages = "com.crdev.bom") // -> 설정파일로써 필요한 빈들을 스켄하게 되어 있음
@EnableWebMvc // -> WebMvcConfigurationSupport의 Spring MVC 구성을 가지게되며, 확장 구현을 위해서는 WebMvcConfigurerAdapter 를 상속하고 확장이 필요한 설정을 오버라이드 하게끔 구성됨
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error/403").setStatusCode(HttpStatus.FORBIDDEN).setViewName("error/403");
        registry.addViewController("/error/404").setStatusCode(HttpStatus.NOT_FOUND).setViewName("error/404");
        registry.addViewController("/error/500").setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR).setViewName("error/500");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}