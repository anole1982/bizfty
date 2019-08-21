package com.bizfty.iot.protrol.editor.service.protrol.caimore;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RegisterMessageHandler extends SimpleChannelInboundHandler<RegisterMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterMessage msg) throws Exception {
        // 简单地打印出server接收到的消息
        System.out.println("接收:"+msg);
    }
}
