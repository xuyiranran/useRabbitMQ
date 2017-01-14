package com.xuyi.useRabbitMQ.topic;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.xuyi.useRabbitMQ.utils.Utils;

/**
 * 
 * @author: xuyi
 * @编码日期: 2017年1月14日 下午8:28:35
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 *
 *       apple生产者
 */
public class ProduceApple
{

    /** 路由名字 */
    private static final String EXCHAGE_NAME = "fruit_exchage";
    /** 路由键 */
    private static final String APPLE_ROUTING_KEY = "routing.fruit.apple";

    public static void main(String[] args) throws IOException
    {

	// 创建连接
	Channel channel = Utils.newChannel();

	// 声明路由名和类型
	channel.exchangeDeclare(EXCHAGE_NAME, "topic");

	// 消息载体(有效数据)
	String message = " create apple ";

	// 生产消息
	channel.basicPublish(EXCHAGE_NAME, APPLE_ROUTING_KEY, null, message.getBytes());
	System.out.println("生产apple消息成功...");

    }

}
