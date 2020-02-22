package com.home.mbm.ps;

import com.home.mbm.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
* @Package: com.home.mbm.ps
* @ClassName: P
* @Description: RabbirMq 生产者
* @Author: mbm
* @date: 2020/2/22 13:50
* @Version: 1.0
*/
public class P {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException {

        // 获取连姐
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 创建 交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        // 消息内容
        String message = "Hello-World";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        // 通道关闭
        channel.close();
        // 连接关闭
        conn.close();
    }

}
