# RibbitMQ_git
学习RibbitMQ示例代码
工作模式：
执行密集型任务的时候先交给队列，让多个工作线程同时处理，一个消息只能被一个消费者处理一次，不可处理多次，工作线程是轮询且竞争的关系
  记错处理：
  Caused by: com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<chan
代表没有相应的信道名称与之消费。生产者生产一个相同名称的信道即可

消息应答：
Rabbit一旦像消费者发送一条消息，该消息就被标记为删除，为了避免其中出现的消息丢失，Rabbit引入消息应答机制，在消费者处理完消息之后，告诉Rabbit可以删除了。
    自动应答：环境条件要求较高
    手动应答：Channel.basicAck。肯定确认
    Channel.basicNack。否定确认
    Channel.Reject,拒绝确认
    手动可以处理批量应答。
重新入列：只要是没有channel.basicAck，进行消息确认或者消息（消费者）断开链接了。ribbit了解到消息未被完全处理，会将其重新排队。
