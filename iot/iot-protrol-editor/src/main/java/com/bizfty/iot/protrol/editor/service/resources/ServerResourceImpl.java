package com.bizfty.iot.protrol.editor.service.resources;

import com.bizfty.iot.protrol.editor.service.services.Server;
import com.bizfty.protrol.editor.service.api.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerResourceImpl implements ServerResource {

    @Autowired
    private Server server;
    
    @Override
    public void info() {
        
    }
    
    @Override
    public void start() {
        server.start();
    }

    @Override
    public void shutdown() {
        server.shutdown();
    }

}
