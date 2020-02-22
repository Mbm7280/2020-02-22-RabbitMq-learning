package com.home.mbm.simple;

import com.home.mbm.utils.ConnUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
* @Package: com.home.mbm.simple
* @ClassName: P
* @Description: 信息的创建者
* @Author: mbm
* @date: 2020/2/22 12:11
* @Version: 1.0
*/
public class P {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException {

        // 获取连接
        Connection conn = ConnUtils.getConn();
        // 从连接中创建通道
        Channel channel = conn.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 定义消息内容
        String message = "Hello-World";
        // 发送消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        // 控制台打印
        System.out.println(" [x] Sent '" + message + "'");

        // 关闭通道
        channel.close();
        // 关闭连接
        conn.close();

    }

}
