package com.home.mbm.simple;

import com.home.mbm.utils.ConnUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
* @Package: com.home.mbm.simple
* @ClassName: C
* @Description: RabbitMq 的消费者
* @Author: mbm
* @date: 2020/2/22 12:21
* @Version: 1.0
*/
public class C {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtils.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列，为了防止BUg 如果此队列已经存在，则改命令无效。 如果该队列不存在，则创建。
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

        // 获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message  = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }

    }

}
