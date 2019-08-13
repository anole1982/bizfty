package com.bizfty.service.container.editor.application;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

}
