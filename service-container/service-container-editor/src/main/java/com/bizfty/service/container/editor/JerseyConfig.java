package com.bizfty.service.container.editor;

import javax.ws.rs.ApplicationPath;
import com.bizfty.service.container.editor.resources.ServiceResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ServiceResource.class);
        register(JacksonFeature.class);
        Set<Class<?>> resources = new HashSet<>();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        registerClasses(resources);
    }
}

