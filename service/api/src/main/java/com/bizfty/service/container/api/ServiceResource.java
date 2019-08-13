package com.bizfty.service.container.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "server-service")
public interface ServiceResource {
    
    public String getServerName();
    
}
