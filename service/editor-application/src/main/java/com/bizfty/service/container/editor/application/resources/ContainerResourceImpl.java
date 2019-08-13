package com.bizfty.service.container.editor.application.resources;

import com.bizfty.service.container.editor.application.services.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@Path("/container")
@Tag(name = "/container", description = "容器管理")
public class ContainerResourceImpl {

    @Autowired
    private ContainerService containerService;
    
    @Operation(
            summary = "获取服务器名称",
            description = "测试负载均衡,通过Feign的客户端负载均衡",
            responses = {
                    @ApiResponse(responseCode = "200", description = "获取服务器名称")
            })
    @GET
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String[] getBeanNames(){
        return containerService.getBeanNames();
    }

}
