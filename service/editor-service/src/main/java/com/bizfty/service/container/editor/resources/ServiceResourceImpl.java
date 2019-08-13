package com.bizfty.service.container.editor.resources;

import com.bizfty.service.container.editor.service.api.ServiceResource;
import com.bizfty.service.container.editor.services.ServiceMgmtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
public class ServiceResourceImpl implements ServiceResource {

    @Resource
    private ServiceMgmtService service;

    @Value("${mykey.name}")
    private String name;

    @Override
    public  String getServerName() {
        Map<String, String> map = System.getenv();
        String cname = map.get("COMPUTERNAME");
        return cname;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/name")
    public String getName(){
        return this.name;
    }

}
