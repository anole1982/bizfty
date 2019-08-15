package com.bizfty.iot.protrol.editor.service;

import com.bizfty.iot.protrol.editor.service.resources.ServerResourceImpl;
import javax.ws.rs.ApplicationPath;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/rests")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ServerResourceImpl.class);
        register(JacksonFeature.class);
        Set<Class<?>> resources = new HashSet<>();
        property(ServerProperties.TRACING, TracingConfig.ON_DEMAND.name());
        resources.add(OpenApiResource.class);
        register(MultiPartFeature.class);
        registerClasses(resources);
    }
}
