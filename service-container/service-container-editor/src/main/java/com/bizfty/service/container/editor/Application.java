package com.bizfty.service.container.editor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.Arrays;
import java.util.EnumSet;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean characterEncoding() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean crossOrigin() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CrossOriginFilter crossOriginFilter = new CrossOriginFilter();
        registrationBean.setFilter(crossOriginFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.addInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        registrationBean.addInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        registrationBean.addInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        registrationBean.addInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        registrationBean.addInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        return registrationBean;
    }
}
