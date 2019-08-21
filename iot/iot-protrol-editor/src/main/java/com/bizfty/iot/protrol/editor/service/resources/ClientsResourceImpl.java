package com.bizfty.iot.protrol.editor.service.resources;

import com.bizfty.iot.protrol.editor.service.protrol.caimore.ChannelPool;
import com.bizfty.iot.protrol.editor.service.protrol.caimore.RegisterMessage;
import com.bizfty.protrol.editor.service.api.ClientsResource;
import com.bizfty.protrol.editor.service.api.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientsResourceImpl implements ClientsResource {

    @Autowired
    private ChannelPool channelPool;

    public Collection<Client> clients(){
        List<Client> clients = new ArrayList<>();
        for(Client t: channelPool.list()){
            clients.add(t);
        }
        return clients;
    }

}
