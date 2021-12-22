package com.yangsiyuan.rabbitm.one;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * 生产者
 */
public class Product {
    //队列名称
    public  final  static String queueNAme = "hello";

    public static void main(String[] args)throws Exception {
        //1.定义工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.获取工厂Ip
        factory.setHost("192.168.59.128");
        //3.用户名
        factory.setUsername("admin");
        //4.密码
        factory.setPassword("123");
            //5.创建链接通信
            Connection conn = factory.newConnection();
            //6.获取信道
            Channel channel = conn.createChannel();
            /*
             * 生成一个队列
             * 参数
             * 1、队列名称
             * 2、消息是否持久化:MessageProperties.PERSISTENT_TEXT_PLAIN(持久化参数)
             * 3、队列是否给一个消费者使用。ture多个消费者使用，false一个消费者
             * 4、是否自动删除，最后一个断开以后是否断开，true自动删除
             * 5、其他参数
             */
            channel.queueDeclare(queueNAme,true,false,false,null);
            //7.定义消息
            String message = "hello world";
            //8.发消息，使用信道发
            /*
             * 参数：
             * 1、表示发送到哪个交换机
             * 2、路由的key值是那个，本次是队列名称、
             * 3、其他参数信息
             * 4、消息体
             */
            channel.basicQos(1);//不公平分发
            channel.basicPublish("",queueNAme, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));

            System.out.println("消息发送成功");
    }
}
