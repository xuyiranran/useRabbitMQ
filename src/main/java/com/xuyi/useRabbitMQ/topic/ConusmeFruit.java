package com.xuyi.useRabbitMQ.topic;

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
 * @编码日期: 2017年1月14日 下午8:29:10
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 *
 *       水果消费者
 * 
 * @备注:
 *
 *      topic主题模式的实现方式主要是依靠routing_key的灵活匹配<br>
 *      "*"表示一个单词,"#"表示任意字符.使用"."来做分割<br>
 */
public class ConusmeFruit
{

    /** 路由名字 */
    private static final String EXCHAGE_NAME = "fruit_exchage";
    /** 路由键 */
    private static final String FRUIT_ROUTING_KEY = "routing.fruit.#";
    /** 队列名 */
    private static final String FRUIT_QUEUE_NAME = "fruit_queue_name";

    public static void main(String[] args) throws IOException
    {

	// 创建连接
	Channel channel = Utils.newChannel();

	// 声明路由名和类型
	channel.exchangeDeclare(EXCHAGE_NAME, "topic");
	channel.queueDeclare(FRUIT_QUEUE_NAME, false, false, false, null);
	channel.queueBind(FRUIT_QUEUE_NAME, EXCHAGE_NAME, FRUIT_ROUTING_KEY);

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
	channel.basicConsume(FRUIT_QUEUE_NAME, true, callback);

    }

}
