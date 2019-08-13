package com.bizfty.service.container.editor.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    @Autowired
    RestTemplateBuilder builder;

    public RibbonConfig(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return builder.build();
    }
}
