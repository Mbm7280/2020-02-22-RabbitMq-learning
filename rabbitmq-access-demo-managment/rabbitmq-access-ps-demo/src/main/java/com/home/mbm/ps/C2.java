package com.home.mbm.ps;

import com.home.mbm.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
* @Package: com.home.mbm.ps
* @ClassName: C2
* @Description: RabbitMq 消费者-2
* @Author: mbm
* @date: 2020/2/22 14:07
* @Version: 1.0
*/
public class C2 {

    private final static String QUEUE_NAME = "test_queue_ps_2";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，设置手动返回完成
        channel.basicConsume(QUEUE_NAME,false,consumer);

        // 获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            // 线程休息 1秒
            Thread.sleep(1000);
            // 手动返回状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }

}
