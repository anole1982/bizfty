package com.bizfty.service.container.editor.application.resources;

import com.bizfty.service.container.editor.application.api.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ServiceResourceImpl implements ServiceResource {
    
    @Autowired
    private com.bizfty.service.container.editor.service.api.ServiceResource service;
    
    @Override
    public  String getServerName() {
        return service.getServerName();
    }
    
}
