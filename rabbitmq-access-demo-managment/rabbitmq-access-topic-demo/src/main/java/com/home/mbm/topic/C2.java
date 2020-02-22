package com.home.mbm.topic;

import com.home.mbm.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
* @Package: com.home.mbm.topic
* @ClassName: C2
* @Description: Rabbitnq 消费者-2
* @Author: mbm
* @date: 2020/2/22 16:19
* @Version: 1.0
*/
public class C2 {

    private final static String QUEUE_NAME = "test_queue_topic_2";

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.insert");

        // 定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列 , 手动返回完成
        channel.basicConsume(QUEUE_NAME,false,consumer);

        // 获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" 搜索项目: '" + message + "'");
            // 线程休息 1秒
            Thread.sleep(1000);
            // 手动返回状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }

}
