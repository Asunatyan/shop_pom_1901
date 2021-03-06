package com.qf.demo3;

import com.qf.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 *
 * @version 1.0
 * @user ken
 * @date 2019/5/24 10:15
 */
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        //1、连接Rabbitmq
        Connection connection = ConnectionUtil.getConnection();

        //2、通过连接获得通道对象，后续的所有操作，都是通过通道对象操作的
        Channel channel = connection.createChannel();

        //3、创建一个交换机
        //交换机的名称和交换机的类型
        channel.exchangeDeclare("myexchange", "fanout");

        //4、给队列中发送消息
        String msg = "Hello RabbitMQ Fanout!";
        channel.basicPublish("myexchange", "", null, msg.getBytes("utf-8"));

        connection.close();

        System.out.println("当前提供者已经执行完成！");
    }
}
