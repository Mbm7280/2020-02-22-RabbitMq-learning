package com.home.mbm.work;

import com.home.mbm.utils.ConnUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
* @Package: com.home.mbm.work
* @ClassName: C2
* @Description: RabbitMq 的消费者-2
* @Author: mbm
* @date: 2020/2/22 13:08
* @Version: 1.0
*/
public class C2 {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtils.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列， 手动返回完成
        channel.basicConsume(QUEUE_NAME,false,consumer);

        // 获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");

            // 休眠 1秒
            Thread.sleep(1000);
            // 返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }

    }

}
