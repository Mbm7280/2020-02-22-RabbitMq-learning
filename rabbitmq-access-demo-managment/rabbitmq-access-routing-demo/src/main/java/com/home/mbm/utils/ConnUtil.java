package com.home.mbm.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
* @Package: com.home.mbm.utils
* @ClassName: ConnUtil
* @Description: 获取 Rabbit 连接的工具类
* @Author: mbm
* @date: 2020/2/22 15:29
* @Version: 1.0
*/
public class ConnUtil {

    public static Connection getConn() throws IOException {

        // 创建 获取连接 的工厂类
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 host
        factory.setHost("localhost");
        // 设置 port
        factory.setPort(5672);
        // 设置 vh
        factory.setVirtualHost("/admin");
        // 设置 UserName
        factory.setUsername("admin");
        // 设置 Password
        factory.setPassword("admin");
        // 定义用于 返回对象
        Connection conn = factory.newConnection();
        // return
        return conn;
    }

}
