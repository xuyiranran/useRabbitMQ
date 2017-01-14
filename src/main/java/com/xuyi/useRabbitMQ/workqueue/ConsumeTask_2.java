package com.xuyi.useRabbitMQ.workqueue;

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
 * @编码日期: 2017年1月14日 下午7:22:36
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 * @消费者2
 */
public class ConsumeTask_2
{
    private static final String TASK_QUEUE = "task.queue";

    public static void main(String[] args) throws IOException
    {
	// 创建连接
	Channel channel = Utils.newChannel();

	// 声明队列
	channel.queueDeclare(TASK_QUEUE, false, false, false, null);

	// 回调函数
	Consumer callback = new DefaultConsumer(channel)
	{
	    @Override
	    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
		    throws IOException
	    {

		try
		{
		    Thread.sleep(1000);
		} catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
		String message = new String(body);
		System.out.println(Thread.currentThread().getName() + "receive message:" + message);
	    }
	};

	// 消费者消费消息
	channel.basicConsume(TASK_QUEUE, true, callback);
    }
}
