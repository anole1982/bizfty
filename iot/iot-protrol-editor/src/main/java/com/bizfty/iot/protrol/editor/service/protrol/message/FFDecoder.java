package com.bizfty.iot.protrol.editor.service.protrol.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class FFDecoder extends ByteToMessageDecoder {

    private final static int HEADER_LENGTH = 44;// header的长度

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 长度不足，退出
        if (in.readableBytes() < HEADER_LENGTH) {
            return;
        }
        // 获取协议的版本
        int version = in.readInt();
        // 获取消息长度
        int contentLength = in.readInt();
        // 获取SessionId
        byte[] sessionByte = new byte[36];
        in.readBytes(sessionByte);
        String sessionId = new String(sessionByte);
        // 组装协议头
        FFHeader header = new FFHeader(version, contentLength, sessionId);

        // 长度不足重置读index，退出
        if (in.readableBytes() < contentLength) {
            in.setIndex(in.readerIndex() - HEADER_LENGTH, in.writerIndex());
            return;
        }

        byte[] content = new byte[contentLength];
        // 读取消息内容
        in.readBytes(content);

        FFMessage message = new FFMessage(header, new String(content));

        out.add(message);
    }
}