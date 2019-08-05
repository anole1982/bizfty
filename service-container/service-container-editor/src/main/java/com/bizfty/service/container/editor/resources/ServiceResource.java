package com.bizfty.service.container.editor.resources;

import com.bizfty.service.container.editor.services.ServiceMgmtService;
import org.springframework.stereotype.Component;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "/service", description = "服务管理接口")
public class ServiceResource {
    @Resource
    private ServiceMgmtService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "获取服务名称",
            description = "获取服务名称",
            responses = {
                    @ApiResponse(responseCode = "204", description = "建立数据模型成功")
            })
    public  String getService() {
        return service.getServiceName();
    }
}
