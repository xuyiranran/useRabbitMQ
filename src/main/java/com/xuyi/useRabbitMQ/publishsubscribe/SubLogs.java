package com.xuyi.useRabbitMQ.publishsubscribe;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.xuyi.useRabbitMQ.utils.Utils;

/**
 * 
 * @author: xuyi
 * @编码日期: 2017年1月14日 下午8:02:57
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成 <br>
 * 
 * @发布订阅模式往往用于广播模式 @消费者可以轻松实现订阅自己感兴趣的消息.
 * 
 *                 关键点在于订阅者绑定的队列声明
 */
public class SubLogs
{
    private static final String EXCHAGE_NAME_INFO = "logs_info";
    private static final String EXCHAGE_NAME_ERROR = "logs_error";
    private static final String QUEUE_NAME = "log_queue";

    public static void main(String[] args) throws IOException
    {
	// 创建连接
	Channel channel = Utils.newChannel();

	// 申明路由名和路由类型
	channel.queueDeclare(QUEUE_NAME, false, false, false, null);

	// 绑定队列
	// 订阅info级别的日志
	channel.queueBind(QUEUE_NAME, EXCHAGE_NAME_INFO, "");
	// 订阅error级别的日志
	channel.queueBind(QUEUE_NAME, EXCHAGE_NAME_ERROR, "");

	// 回调函数
	Consumer callback = new DefaultConsumer(channel)
	{
	    @Override
	    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
		    throws IOException
	    {
		String message = new String(body);
		System.out.println("receive message:" + message);
	    }
	};

	// 消费者消费消息
	channel.basicConsume(QUEUE_NAME, true, callback);

    }

}
