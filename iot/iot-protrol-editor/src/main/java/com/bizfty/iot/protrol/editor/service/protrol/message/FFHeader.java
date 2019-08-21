package com.bizfty.iot.protrol.editor.service.protrol.message;

//消息的头部
public class FFHeader {

    // 协议版本
    private int version;
    // 消息内容长度
    private int contentLength;
    // 服务名称
    private String sessionId;

    public FFHeader(int version, int contentLength, String sessionId) {
        this.version = version;
        this.sessionId = sessionId;
        this.contentLength = contentLength;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
