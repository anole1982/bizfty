package com.bizfty.iot.protrol.editor.service.services;

import com.bizfty.iot.protrol.editor.service.websocket.ChannelEndpoint;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class WebsocketPool {

    private CopyOnWriteArraySet<ChannelEndpoint> webSocketSet = new CopyOnWriteArraySet<ChannelEndpoint>();

    public void registration(ChannelEndpoint endpoint){
        webSocketSet.add(endpoint);
    }

    public void cancellation(ChannelEndpoint endpoint){
        webSocketSet.remove(endpoint);
    }

    public void broadcast(String message){

    }

    public void message(){

    }
}
