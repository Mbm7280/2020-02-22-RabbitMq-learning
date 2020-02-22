package com.home.mbm.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
* @Package: com.home.mbm.utils
* @ClassName: ConnUtil
* @Description:  获取 Rabbitmq 连接的工具类
* @Author: mbm
* @date: 2020/2/22 16:03
* @Version: 1.0
*/
public class ConnUtil {

    public static Connection getConn() throws IOException {
        //  创建 生产 连接的 工厂对象
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 host
        factory.setHost("localhost");
        // 设置 port
        factory.setPort(5672);
        // 设置 vs
        factory.setVirtualHost("/admin");
        // 设置 username
        factory.setUsername("admin");
        // 设置 password
        factory.setPassword("admin");
        // 创建用于接收返回值的对象
        Connection conn = factory.newConnection();
        // return
        return conn;
    }

}
