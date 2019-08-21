package com.bizfty.iot.protrol.editor.service.services.netty;

import com.bizfty.iot.protrol.editor.service.protrol.message.FFDecoder;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFEncoder;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFHeader;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

public class Client {

    public static void main(String args[]) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    // 添加编码器
                    pipeline.addLast(new FFEncoder());
                    // 添加解码器
                    pipeline.addLast(new FFDecoder());
                    // 业务处理类（只打印了消息内容）
                    pipeline.addLast(new ClientHandler());
                }
            });

            // 连接服务端
            Channel ch = b.connect("192.168.50.3", 3222).sync().channel();
            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String str = "Hello!";

            // 发送1000000条消息
            for (int i = 0; i < 100000; i++) {
                String content = str + "----" + i;
                FFHeader header = new FFHeader(version, content.length(), sessionId);
                FFMessage message = new FFMessage(header, content);
                ch.writeAndFlush(message);
            }

            ch.closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }
}