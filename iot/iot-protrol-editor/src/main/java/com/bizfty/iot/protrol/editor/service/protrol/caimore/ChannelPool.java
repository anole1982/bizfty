package com.bizfty.iot.protrol.editor.service.protrol.caimore;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ChannelPool {

    private static final Logger logger = LoggerFactory.getLogger(ChannelPool.class);
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private ConcurrentHashMap<Channel,RegisterMessage> pool = new ConcurrentHashMap();

    private AtomicInteger idCount = new AtomicInteger(0);

    public void registration(Channel channel,RegisterMessage message) throws Exception {
        pool.put(channel,message);
    }
    public void cancellation(Channel channel){
        try {
            rwLock.writeLock().lock();
            pool.remove(channel);
        }finally {
            rwLock.writeLock().unlock();
        }
    }
    public void broadcast(byte[] message){
        try {
            rwLock.writeLock().lock();
            Set<Channel> keySet = pool.keySet();
            for (Channel ch : keySet) {
                ch.writeAndFlush(message);
            }
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void message(byte[] message,String id){
        try {
            rwLock.writeLock().lock();
            Channel channel = null;
            Set<Channel> keySet = pool.keySet();
            RegisterMessage register;
            for (Channel ch : keySet) {
                register = pool.get(ch);
                if(register.getId().equals(id)){
                    ch.writeAndFlush(message);
                }
                break;
            }
        }finally {
            rwLock.writeLock().unlock();
        }
    }
    public Collection<RegisterMessage> list(){
        return pool.values();
    }
}
