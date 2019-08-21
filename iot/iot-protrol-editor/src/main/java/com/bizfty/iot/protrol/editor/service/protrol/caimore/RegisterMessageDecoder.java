package com.bizfty.iot.protrol.editor.service.protrol.caimore;

import com.bizfty.iot.protrol.editor.service.protrol.message.FFHeader;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;

/**
 *  4 位 设备ID
 *  13位 手机号码
 *  1位 分割符 0
 *  4位 设备id
 *  1位 分隔符 0
 *
 */
@Component
@Scope("prototype")
public class RegisterMessageDecoder extends ByteToMessageDecoder {

    @Autowired
    private ChannelPool channelPool;

    private final static int LENGTH = 21;// 注册信息长度
    private final static Logger logger = LoggerFactory.getLogger(RegisterMessageDecoder.class);
    private AttributeKey<Boolean> keyRegister = AttributeKey.valueOf("register");
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //当前处于注册状态
        if(!ctx.channel().attr(keyRegister).get()){
            return;
        }
        // 长度不足，退出
        if (in.readableBytes() < LENGTH) {
            return;
        }
        // 获取ID
        byte[] idb = new byte[4];
        idb[3] = in.readByte();
        idb[2] = in.readByte();
        idb[1] = in.readByte();
        idb[0] = in.readByte();
        String id = Hex.encodeHexString(idb);
        // 获取电话号码
        byte[] tel = new byte[11];
        in.readBytes(tel);
        byte[] separator = new byte[1];
        in.readBytes(separator);
        byte[] ip = new byte[4];
        in.readBytes(ip);
        InetAddress inetAddr = InetAddress.getByAddress(ip);
        in.readBytes(separator);
        RegisterMessage message = new RegisterMessage(new String(tel),id,inetAddr.getHostAddress());
        out.add(message);
        Attribute<Boolean> register = ctx.channel().attr(keyRegister);
        register.set(Boolean.FALSE);
        channelPool.registration(ctx.channel(),message);
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Attribute<Boolean> register;
        logger.debug("新建连接：channelActive");
        register = ctx.channel().attr(keyRegister);
        register.set(Boolean.TRUE);
    }
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        channelPool.cancellation(ctx.channel());
        logger.debug("channelInactive");
    }
}
