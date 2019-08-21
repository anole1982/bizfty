package com.bizfty.iot.protrol.editor.service.protrol.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayOutputStream;

public class FFEncoder extends MessageToByteEncoder<FFMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, FFMessage message, ByteBuf out) throws Exception {

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        // 将Message转换成二进制数据
        FFHeader header = message.getLuckHeader();
        // 写入Header信息
        out.writeInt(header.getVersion());
        o.write(header.getVersion());
        out.writeInt(header.getContentLength());
        o.write(header.getContentLength());
        out.writeBytes(header.getSessionId().getBytes());
        o.write(header.getSessionId().getBytes());
        o.write(message.getContent().getBytes());
        System.out.println(Hex.toHexString(o.toByteArray()));
        // 写入消息主体信息
        out.writeBytes(message.getContent().getBytes());
    }
}