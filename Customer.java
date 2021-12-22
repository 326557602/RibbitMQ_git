package com.yangsiyuan.rabbitm.one;

import com.rabbitmq.client.*;

/**
 * 消费者，接受消息
 */
public class Customer {
    //队列名称要与生产者一致
    public  final static String queueName="hello";

    public static void main(String[] args) throws Exception {
        //1.定义工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.获取链接
        factory.setHost("192.168.59.128");
        //3.获取用户名
        factory.setUsername("admin");
        //4.获取密码
        factory.setPassword("123");
        //5.创建链接
        Connection conn = factory.newConnection();
        //6.定义信道
        Channel channel = conn.createChannel();
        //7.消费者消费信息
        /*
         * 参数：
         * 1、消费那个队列
         * 2.消费成功之后是否要自动应答，true代表自动应答，false代表不自动应答
         * 3、消费者未成功的消费回调(函数式接口)
         * 4.消费者取消消费的回调
         */
        //lambda表达式
        //消费成功
        DeliverCallback deliverCallback = (consumerTag,deliver)->{
            String message = new String(deliver.getBody()); //获取消息体
            System.out.println(message);
        };
        //消费失败
        CancelCallback cancelCallback = consumerTag->{
            System.out.println("消费消息被中断");
        };
        System.out.println("工作线程222");
        channel.basicConsume(queueName,false,deliverCallback,cancelCallback);
    }
}
