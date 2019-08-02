package com.bizfty.service.container.editor.resources;

import com.bizfty.service.container.editor.services.ServiceMgmtService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "service")
@Path("/service")
public class ServiceResource {
    @Resource
    private ServiceMgmtService service;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  String getService() {
        return service.getServiceName();
    }
}
