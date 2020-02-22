package com.home.mbm.routing;

import com.home.mbm.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
* @Package: com.home.mbm.routing
* @ClassName: C
* @Description: RabbitMq 消费者-1
* @Author: mbm
* @date: 2020/2/22 15:39
* @Version: 1.0
*/
public class C {

    private final static String QUEUE_NAME = "test_queue_routing_1";

    private final static String EXCHANGE_NAME = "test_exchange_routing";

    public static void main(String[] args) throws IOException, InterruptedException {

        // 获取连接
        Connection conn = ConnUtil.getConn();
        // 创建通道
        Channel channel = conn.createChannel();
        // 创建 队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 绑定交换机
        // 第三个参数 设置 key 值
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"insert");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"delete");

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME,false,consumer);

        // 获取消息
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" 前台系统 '" + message + "'");

            // 线程休息 10毫秒
            Thread.sleep(10);
            // 手动返回 状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }

    }

}
