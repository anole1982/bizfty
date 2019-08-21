package com.bizfty.iot.protrol.editor.service.services.netty;

import com.bizfty.iot.protrol.editor.service.protrol.caimore.RegisterMessageDecoder;
import com.bizfty.iot.protrol.editor.service.protrol.caimore.RegisterMessageHandler;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFDecoder;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFEncoder;
import com.bizfty.iot.protrol.editor.service.protrol.message.FFMessageHander;
import com.bizfty.iot.protrol.editor.service.services.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.SystemPropertyUtil;
import java.security.KeyStore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.util.internal.logging.InternalLogLevel;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.stereotype.Service;

@Service
public class ServerNettyImpl implements Server {

    private static final Logger logger = LoggerFactory.getLogger(ServerNettyImpl.class);
    private static final String PROTOCOL = "TLS";
    private SSLContext SERVER_CONTEXT;
    @Autowired
    private NettyConfig config;
    @Autowired
    private InetUtilsProperties inetUtilsProperties;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap bootstrap = null;// 启动辅助类
    private InetUtils inetUtils;

    @Override
    public void start() {
        init();
        bootstrap.group(bossGroup, workGroup)
                .channel(useEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, config.isReuseAddr())
                .option(ChannelOption.SO_BACKLOG, config.getBacklog())
                .option(ChannelOption.SO_RCVBUF, config.getRevbuf())
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        initHandler(ch.pipeline());
                    }
                })
                .childOption(ChannelOption.TCP_NODELAY, config.isTcpNoDelay())
                .childOption(ChannelOption.SO_KEEPALIVE, config.isKeepAlive())
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.bind(inetUtils.findFirstNonLoopbackHostInfo().getIpAddress(), config.getTcpPort()).addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                logger.info("服务端启动成功【" + inetUtils.findFirstNonLoopbackHostInfo().getIpAddress() + ":" + config.getTcpPort() + "】");
            } else {
                logger.info("服务端启动失败【" + inetUtils.findFirstNonLoopbackHostInfo().getIpAddress() + ":" + config.getTcpPort() + "】");
            }
        });
    }

    @Override
    public void shutdown() {
        if (workGroup != null && bossGroup != null) {
            try {
                bossGroup.shutdownGracefully().sync();// 优雅关闭
                workGroup.shutdownGracefully().sync();
                logger.info("服务端成功关闭【" + inetUtils.findFirstNonLoopbackHostInfo().getIpAddress() + ":" + config.getTcpPort() + "】");
            } catch (InterruptedException e) {
                logger.info("服务端关闭资源失败【" + inetUtils.findFirstNonLoopbackHostInfo().getIpAddress() + ":" + config.getTcpPort() + "】");
            }
        }
    }

    protected void initHandler(ChannelPipeline channelPipeline) {
        if (config.isSsl()) {
            if (!ObjectUtils.allNotNull(config.getJksCertificatePassword(), config.getJksFile(), config.getJksStorePassword())) {
                throw new NullPointerException("SSL file and password is null");
            }
            initSsl();
            SSLEngine engine = SERVER_CONTEXT.createSSLEngine();
            engine.setUseClientMode(false);
            channelPipeline.addLast("ssl", new SslHandler(engine));
        }

        intProtocolHandler(channelPipeline);

    }

    private void intProtocolHandler(ChannelPipeline channelPipeline) {
        channelPipeline.addLast(new IdleStateHandler(config.getHeart(), 0, 0));
        channelPipeline.addLast("logging", new LoggingHandler());
        channelPipeline.addLast("regdecoder", registerMessageDecoder());
        channelPipeline.addLast("reghanler", registerMessageHandler());
    }

    protected void initSsl() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
        });
        String algorithm = SystemPropertyUtil.get("ssl.KeyManagerFactory.algorithm");
        if (algorithm == null) {
            algorithm = "SunX509";
        }
        SSLContext serverContext;
        try {
            //
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(Thread.class.getResourceAsStream(config.getJksFile()),
                    config.getJksStorePassword().toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            kmf.init(ks, config.getJksCertificatePassword().toCharArray());
            serverContext = SSLContext.getInstance(PROTOCOL);
            serverContext.init(kmf.getKeyManagers(), null, null);
        } catch (Exception e) {
            throw new Error(
                    "Failed to initialize the server-side SSLContext", e);
        }
        SERVER_CONTEXT = serverContext;
    }

    private void init() {
        inetUtils = new InetUtils(inetUtilsProperties);
        bootstrap = new ServerBootstrap();
        if (useEpoll()) {
            bossGroup = new EpollEventLoopGroup(config.getBossCount(), new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);

                public Thread newThread(Runnable r) {
                    return new Thread(r, "LINUX_BOSS_" + index.incrementAndGet());
                }
            });
            workGroup = new EpollEventLoopGroup(config.getWorkerCount(), new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);

                public Thread newThread(Runnable r) {
                    return new Thread(r, "LINUX_WORK_" + index.incrementAndGet());
                }
            });
        } else {
            bossGroup = new NioEventLoopGroup(config.getBossCount(), new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);

                public Thread newThread(Runnable r) {
                    return new Thread(r, "BOSS_" + index.incrementAndGet());
                }
            });
            workGroup = new NioEventLoopGroup(config.getWorkerCount(), new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);

                public Thread newThread(Runnable r) {
                    return new Thread(r, "WORK_" + index.incrementAndGet());
                }
            });
        }
    }

    private boolean useEpoll() {
        return Epoll.isAvailable();
    }

    @Autowired
    private Provider<RegisterMessageDecoder> registerMessageDecoderProvider;
    @Autowired
    private Provider<RegisterMessageHandler> registerMessageHandlerProvider;

    private RegisterMessageDecoder registerMessageDecoder(){
        return registerMessageDecoderProvider.get();
    }

    private RegisterMessageHandler registerMessageHandler(){
        return registerMessageHandlerProvider.get();
    }
}
