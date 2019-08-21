package com.bizfty.iot.protrol.editor.service.protrol.caimore;

import com.bizfty.protrol.editor.service.api.beans.Client;

public class RegisterMessage implements Client {
    private String tel;
    private String ip;
    private String id;
    public RegisterMessage(String tel, String id, String ip){
        this.tel = tel;
        this.id = id;
        this.ip = ip;
    }

    public String getTel() {
        return tel;
    }

    public String getId() {
        return id;
    }
    public String getIp() {
        return ip;
    }
    public String toString(){
        return "当前DTU为: tel=" +tel +",id=" +id +",ip=" +ip;
    }
}
