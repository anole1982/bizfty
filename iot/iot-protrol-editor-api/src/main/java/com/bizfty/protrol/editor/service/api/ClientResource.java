package com.bizfty.protrol.editor.service.api;

import com.bizfty.protrol.editor.service.api.beans.Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

@FeignClient(name="iot-protrol-editer-service",path="rests")
@Path("/client")
@Tag(name = "/client/{id}", description = "客户端管理接口")
public interface ClientResource {
    @GET
    @Path("/")
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Client info(@PathParam("id") String id);
}
