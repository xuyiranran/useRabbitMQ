package com.xuyi.useRabbitMQ.helloworld;

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
 * @编码日期: 2017年1月14日 下午6:49:37
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 *
 * @消费者消费消息的方式<br>
 *
 * 		1、channel.basicGet(queue, autoAck);//主动轮询方式<br>
 *                 2、channel.basicConsume(queue, autoAck, callback);//异步回调方式<br>
 * @备注:推荐使用回调方式,对于消息量比较大的时候方式1效率比较低.
 */
public class ConsumeMessage
{
    private static String QUEUE_NAME = "hello.world.queue";

    public static void main(String[] args) throws IOException
    {

	// 创建连接
	Channel channel = Utils.newChannel();

	// 声明队列
	channel.queueDeclare(QUEUE_NAME, false, false, false, null);

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
