package com.bizfty.service.container.editor.service.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

@FeignClient(name="editor-service",path="rests")
@Path("/service")
@Tag(name = "/service", description = "服务管理接口")
public interface ServiceResource {

    @Operation(
            summary = "获取服务器名称",
            description = "测试负载均衡,通过Feign的客户端负载均衡",
            responses = {
                    @ApiResponse(responseCode = "200", description = "获取服务器名称")
            })
    @GET
    @Consumes(MediaType.ALL_VALUE)
    @Produces(MediaType.TEXT_PLAIN_VALUE)
    public String getServerName();
}
