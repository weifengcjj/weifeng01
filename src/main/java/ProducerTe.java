import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public  class  ProducerTe {
    public static void pro(String body) throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //2.设置参数
        factory.setHost("localhost");//ip 默认值 localhost
        factory.setPort(5672);
        factory.setVirtualHost("/itcast"); //虚拟机 默认值/
        factory.setUsername("weifeng");//用户名
        factory.setPassword("weifeng");//密码 //默认值都是guest
        //3.创建连接 Connection
        Connection connection = factory.newConnection();
        //4. 创建Channel
        Channel channel=connection.createChannel();
        //5.创建队列 Queue
        channel.queueDeclare("weifeng02",true,false,false,null);
        //queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        /*
                //queue 队列名称
                //durable 是否持久化，当mq重启过后，还在
                //exclusive 是否独占，只能有一个消费者监听队列**当Connection关闭时，是否删除队列
                //autoDelete 是否自动删除，没有Consumer时，自动删除
                //arguments 参数
        */

        //6.发送消息
        channel.basicPublish("","weifeng02",null,body.getBytes());
        //basicPublish(String var1, String var2, BasicProperties var3, byte[] var4)
        /*
                //var1:exchange --交换机的名称，简单模式下，交换机会使用默认的
                //var2:routingkey--路由名称
                //var3:props --配置信息
                //var4:body --发送消息
        */

        //7.释放资源
        channel.close();
        connection.close();
    }
    }

