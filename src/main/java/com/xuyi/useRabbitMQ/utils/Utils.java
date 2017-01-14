package com.xuyi.useRabbitMQ.utils;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author: xuyi
 * @编码日期: 2017年1月11日 下午9:50:48
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 *
 */
public class Utils
{

    private static Connection connection;
    private static ConnectionFactory connectionFactory;

    static
    {
	connectionFactory = new ConnectionFactory();
	connectionFactory.setHost("127.0.0.1");
	connectionFactory.setPort(5672);
	connectionFactory.setVirtualHost("test");
	connectionFactory.setUsername("xuyi");
	connectionFactory.setPassword("xuyi");
	try
	{
	    connection = connectionFactory.newConnection();
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * 获得一个新的Channel连接(通道)
     * 
     * @return
     */
    public static Channel newChannel()
    {
	try
	{
	    return connection.createChannel();
	} catch (IOException e)
	{
	    e.printStackTrace();
	}
	return null;
    }

}
