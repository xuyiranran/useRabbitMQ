package com.xuyi.useRabbitMQ.publishsubscribe;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.xuyi.useRabbitMQ.utils.Utils;

/**
 * 
 * @author: xuyi
 * @编码日期: 2017年1月14日 下午7:42:15
 * @功能描述: 发布info级别日志的发布者<br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 *
 */
public class PubLogs_info_lever
{
    private static final String EXCHAGE_NAME = "logs_info";

    public static void main(String[] args) throws IOException
    {

	// 创建连接
	Channel channel = Utils.newChannel();

	// 申明路由名和路由类型
	channel.exchangeDeclare(EXCHAGE_NAME, "fanout");

	String message = "log info message";

	// 生产消息
	channel.basicPublish(EXCHAGE_NAME, "", null, message.getBytes());

	channel.close();

    }
}
