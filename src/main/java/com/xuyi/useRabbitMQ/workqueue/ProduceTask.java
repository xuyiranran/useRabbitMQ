package com.xuyi.useRabbitMQ.workqueue;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.xuyi.useRabbitMQ.utils.Utils;

/**
 * 
 * @author: xuyi
 * @编码日期: 2017年1月14日 下午7:20:57
 * @功能描述: <br>
 * @github https://github.com/xuyiranran <br>
 * @天道酬勤 @水到渠成
 * 
 * 
 * @工作队列
 * 
 *       当生产者生产的任务是需要比较复杂的处理逻辑时或比较费时的话.<br>
 *       那么久需要使用多个消费者来一起处理这些信息,以提高效率。
 *
 */
public class ProduceTask
{

    private static final String TASK_QUEUE = "task.queue";

    public static void main(String[] args) throws IOException
    {

	// 创建连接
	Channel channel = Utils.newChannel();

	for (int i = 0; i < 10; i++)
	{
	    // 消息载体(有效数据)
	    String message = "message" + i;
	    // 生产消息
	    channel.basicPublish("", TASK_QUEUE, null, message.getBytes());
	}
	System.out.println("生产消息成功...");

    }

}
