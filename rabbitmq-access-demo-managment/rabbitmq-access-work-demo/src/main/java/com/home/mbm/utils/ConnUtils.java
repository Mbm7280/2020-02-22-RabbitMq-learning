package com.home.mbm.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
* @Package: com.home.mbm.utils
* @ClassName: ConnUtils
* @Description: RabbitMq 获取连接工具类
* @Author: mbm
* @date: 2020/2/22 12:46
* @Version: 1.0
*/
public class ConnUtils {

    public static Connection getConn() throws IOException {
        // 创建 获取连接的 工厂对象
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 连接地址
        factory.setHost("localhost");
        // 设置 连接端口号
        factory.setPort(5672);
        // 设置 VirtualHost
        factory.setVirtualHost("/admin");
        // 设置 Username
        factory.setUsername("admin");
        // 设置 Password
        factory.setPassword("admin");
        // 创建 Conn 对象
        Connection conn = factory.newConnection();
        // return conn 对象
        return conn;
    }

}
