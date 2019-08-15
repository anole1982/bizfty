package com.bizfty.iot.protrol.editor.service.services.netty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig {
    
    //读取yml中配置 
    @Value("${netty.boss.thread.count}")
    private int bossCount;

    @Value("${netty.worker.thread.count}")
    private int workerCount;

    @Value("${netty.tcp.port}")
    private int tcpPort;
    
    @Value("${netty.tcp.nodelay}")
    private boolean tcpNoDelay;
    
    @Value("${netty.so.keepalive}")
    private boolean keepAlive;
    
    @Value("${netty.so.reuseaddr}")
    private boolean reuseAddr;
    
    @Value("${netty.so.revbuf}")
    private int revbuf;
 
    @Value("${netty.so.sndbuf}")
    private int sndbuf;    
    
    @Value("${netty.so.backlog}")
    private int backlog;
    
    @Value("${netty.ssl}")
    private boolean ssl;
    
    @Value("${netty.jks-file}")
    private String jksFile;
    
    @Value("${netty.jks-store-password}")
    private String jksStorePassword;
    
    @Value("${netty.jks-certificate-password}")
    private String jksCertificatePassword;
    
    @Value("${netty.heart}")
    private int heart;

    public int getBossCount() {
        return bossCount;
    }

    public void setBossCount(int bossCount) {
        this.bossCount = bossCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public boolean isTcpNoDelay() {
        return tcpNoDelay;
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isReuseAddr() {
        return reuseAddr;
    }

    public void setReuseAddr(boolean reuseAddr) {
        this.reuseAddr = reuseAddr;
    }

    public int getRevbuf() {
        return revbuf;
    }

    public void setRevbuf(int revbuf) {
        this.revbuf = revbuf;
    }

    public int getSndbuf() {
        return sndbuf;
    }

    public void setSndbuf(int sndbuf) {
        this.sndbuf = sndbuf;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getJksFile() {
        return jksFile;
    }

    public void setJksFile(String jksFile) {
        this.jksFile = jksFile;
    }

    public String getJksStorePassword() {
        return jksStorePassword;
    }

    public void setJksStorePassword(String jksStorePassword) {
        this.jksStorePassword = jksStorePassword;
    }

    public String getJksCertificatePassword() {
        return jksCertificatePassword;
    }

    public void setJksCertificatePassword(String jksCertificatePassword) {
        this.jksCertificatePassword = jksCertificatePassword;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

}
