package com.home.mbm.topic;

import com.home.mbm.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
* @Package: com.home.mbm.topic
* @ClassName: P
* @Description: RabbitMq 的生产者
* @Author: mbm
* @date: 2020/2/22 16:07
* @Version: 1.0
*/
public class P {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 创建交换机
        // 选择 topic 类型
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        // 消息内容
        String message = "Hello_World_Topic";
        channel.basicPublish(EXCHANGE_NAME,"item.update",null,message.getBytes());
        System.out.println(" 后台系统 '" + message + "'");

        // 关闭通道
        channel.close();
        // 连接关闭
        conn.close();
    }

}
