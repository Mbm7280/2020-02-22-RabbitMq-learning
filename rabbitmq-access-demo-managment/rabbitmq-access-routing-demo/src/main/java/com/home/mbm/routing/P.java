package com.home.mbm.routing;

import com.home.mbm.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
* @Package: com.home.mbm.routing
* @ClassName: P
* @Description: RabbitMq- 生产者
* @Author: mbm
* @date: 2020/2/22 15:29
* @Version: 1.0
*/
public class P {

    private final static String EXCHANGE_NAME = "test_exchange_routing";

    public static void main(String[] args) throws IOException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明交换机
        // 选择 订阅模式的 类型 direct ：路由模式
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        // 消息内容
        // 第二个参数 代表路由条件
        String message = "Hello_World_update";
        String message2 = "Hello_World_insert";
        channel.basicPublish(EXCHANGE_NAME,"update",null,message.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message2.getBytes());
        System.out.println(" 后台系统： '" + message + "'");
        System.out.println(" 后台系统： '" + message2 + "'");


        // 关闭 通道连接
        channel.close();
        // 连接 关闭
        conn.close();

    }

}
