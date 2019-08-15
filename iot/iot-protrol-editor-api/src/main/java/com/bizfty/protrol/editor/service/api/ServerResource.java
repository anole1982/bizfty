package com.bizfty.protrol.editor.service.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

@FeignClient(name="iot-protrol-editer-service",path="rests")
@Path("/server")
@Tag(name = "/server", description = "服务器管理接口")
public interface ServerResource {
    
    @GET
    @Path("/")
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void info();
    
    @GET
    @Path("/start")
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void start();
    
    @GET
    @Path("/shutdown")
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void shutdown();
}
