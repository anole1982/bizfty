package com.bizfty.iot.protrol.editor.service.protrol.dummy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DummyDataHandler extends SimpleChannelInboundHandler<byte[]> {
    private static final Logger logger = LoggerFactory.getLogger(DummyDataHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] message) throws Exception {
        logger.debug("接收到的数据"+ Hex.encode(message));
    }
}
