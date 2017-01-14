package com.xuyi.useRabbitMQ.helloworld;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.xuyi.useRabbitMQ.utils.Utils;

/**
 * 
 * @author: xuyi
 * @编码日期: 2017年1月14日 下午6:49:30
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 *
 */
public class ProduceMessage
{

    private static String QUEUE_NAME = "hello.world.queue";

    public static void main(String[] args) throws IOException
    {

	// 创建连接
	Channel channel = Utils.newChannel();

	// 消息载体(有效数据)
	String message = "hello world,from rabbitMQ!";

	for (int i = 0; i < 10; i++)
	{
	    // 生产消息
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	}
	System.out.println("生产消息成功...");

    }

}
