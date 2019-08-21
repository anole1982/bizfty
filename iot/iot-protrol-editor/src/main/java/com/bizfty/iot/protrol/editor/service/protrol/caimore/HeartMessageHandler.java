package com.bizfty.iot.protrol.editor.service.protrol.caimore;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HeartMessageHandler extends ByteToMessageDecoder {

    private static final byte HEART= (byte)0xFE;

    private final static Logger logger = LoggerFactory.getLogger(RegisterMessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte b = in.readByte();
        if(b == HEART){
            logger.debug("收到心跳");
        }
    }
}