package com.home.mbm.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
* @Package: com.home.mbm.utils
* @ClassName: ConnUtil
* @Description: RabbitMq 获取连接工具类
* @Author: mbm
* @date: 2020/2/22 13:51
* @Version: 1.0
*/
public class ConnUtil {

    public static Connection getConn() throws IOException {
        // 创建 获取连接的 工厂类
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 host
        factory.setHost("localhost");
        // 设置 port
        factory.setPort(5672);
        // 设置 Vs
        factory.setVirtualHost("/admin");
        // 设置 Username
        factory.setUsername("admin");
        // 设置 Password
        factory.setPassword("admin");
        // 创建 Connection 对象用于接收 返回值
        Connection conn  = factory.newConnection();
        // return
        return conn;
    }

}
