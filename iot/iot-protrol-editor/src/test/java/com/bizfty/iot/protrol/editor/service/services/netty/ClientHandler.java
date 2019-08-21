package com.bizfty.iot.protrol.editor.service.services.netty;

import com.bizfty.iot.protrol.editor.service.protrol.message.FFMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<FFMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FFMessage message) throws Exception {
        System.out.println(message);
    }
}