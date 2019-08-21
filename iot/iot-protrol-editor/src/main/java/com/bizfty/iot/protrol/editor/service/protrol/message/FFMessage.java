package com.bizfty.iot.protrol.editor.service.protrol.message;

//消息的主体
public class FFMessage {

    private FFHeader luckHeader;
    private String content;

    public FFMessage(FFHeader luckHeader, String content) {
        this.luckHeader = luckHeader;
        this.content = content;
    }

    public FFHeader getLuckHeader() {
        return luckHeader;
    }

    public void setLuckHeader(FFHeader luckHeader) {
        this.luckHeader = luckHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,contentLength=%d,sessionId=%s,content=%s]", luckHeader.getVersion(),
                luckHeader.getContentLength(), luckHeader.getSessionId(), content);
    }
}
