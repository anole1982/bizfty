//package com.bizfty.iot.protrol.editor.service.services.netty;
//
//import com.bizfty.iot.protrol.editor.service.services.Server;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ConnectException;
//import java.net.Socket;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DisplayName("netty 服务器测试")
//@ExtendWith(SpringExtension.class)
//public class ServerNettyImplTest {
//
//    @Autowired
//    private Server nettyServer;
//
//    @Test
//    @Order(1)
//    void testStart() throws IOException {
//        nettyServer.start();
//        try (Socket socket = new Socket("192.168.50.3", 3222)) {
//            OutputStream outputStream = socket.getOutputStream();
//            String message = "你好  yiwangzhibujian";
//            socket.getOutputStream().write(message.getBytes("UTF-8"));
//            outputStream.close();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testShutdown(){
//        nettyServer.shutdown();
//        ConnectException assertThrows = Assertions.assertThrows(ConnectException.class , ()->{
//            try (Socket socket = new Socket("192.168.50.3", 3222)) {
//                OutputStream outputStream = socket.getOutputStream();
//                String message = "你好  yiwangzhibujian";
//                socket.getOutputStream().write(message.getBytes("UTF-8"));
//                outputStream.close();
//            }
//        });
//    }
//
//}
