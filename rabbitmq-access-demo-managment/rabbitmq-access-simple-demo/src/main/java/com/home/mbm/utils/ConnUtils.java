package com.home.mbm.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
* @Package: com.home.mbm.utils
* @ClassName: ConnUtils
* @Description: Rabbitmq 连接工具类
* @Author: mbm
* @date: 2020/2/22 12:06
* @Version: 1.0
*/
public class ConnUtils {

    public static Connection getConn() throws IOException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("localhost");
        // 设置端口号
        factory.setPort(5672);
        // 设置 账号信息
        factory.setUsername("admin");
        // 设置 账号密码
        factory.setPassword("admin");
        // 设置 vhost
        factory.setVirtualHost("/admin");
        // 获取连接
        Connection conn = factory.newConnection();
        // return 连接
        return conn;
    }

}
