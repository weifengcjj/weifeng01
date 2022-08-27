import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public  class ConsumerTe implements Runnable {
        @Override
        public void run () {
            //1.创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //2.设置参数
            factory.setHost("localhost");//ip 默认值 localhost
            factory.setPort(5672);
            factory.setVirtualHost("/itcast"); //虚拟机 默认值/
            factory.setUsername("weifeng");//用户名
            factory.setPassword("weifeng");//密码 //默认值都是guest
            //3.创建连接 Connection
            Connection connection = null;
            try {
                connection = factory.newConnection();
                //4. 创建Channel
                Channel channel = connection.createChannel();
                //5.创建队列 Queue
                channel.queueDeclare("weifeng02", true, false, false, null);
                //queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        /*
                //queue 队列名称
                //durable 是否持久化，当mq重启过后，还在
                //exclusive 是否独占，只能有一个消费者监听队列**当Connection关闭时，是否删除队列
                //autoDelete 是否自动删除，没有Consumer时，自动删除
                //arguments 参数
        */
                //6.接收消息
                Consumer consumer = new DefaultConsumer(channel) {
                    //回调方法，当收到消息后，会自动执行该方法
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        //consumerTag 消息的标识
                        //envelope 获取一些信息，交换机，路由key。。
                        //body 数据
                        //properties 配置信息
                        System.out.println("consumerTag "+consumerTag);
                        System.out.println("Exchange "+envelope.getExchange());
                        System.out.println("RoutingKey "+envelope.getRoutingKey());
                        System.out.println("properties "+properties);
                        JSONObject object=JSONObject.parseObject(new String(body));
                        String receiveMailAccount=object.getString("receiveMailAccount");
                        Model model=new Model(receiveMailAccount);
                        new Thread(new Email(model)).start();
                        System.out.println("body "+new String(body));
                    }
                };//空的
                channel.basicConsume("weifeng02", true, consumer);
                //basicConsume(String var1, boolean var2, Consumer var3)
                //var1-queue 队列名称
                //var2-autoAck 是否自动确认//var3-callback 回调的对象
                Thread.sleep(5000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//1.调度器（Scheduler）,从工厂中获取调度的实例 （默认：实例化 new StdSchedulerFactory(); ）
//            Scheduler scheduler = null;
//            try {
//                scheduler = StdSchedulerFactory.getDefaultScheduler();
//                //2.任务实例（JobDetail）
//                JobDetail jobDetail = JobBuilder.newJob(Myjob.class)     //加载任务类，与HelloJob完成绑定，要求HelloJob实现Job接口
//                        .withIdentity("job1", "group1")   //参数1：任务的名称(唯一实例) 参数2：任务组的名称
//                        .build();
//                //3.触发器（Trigger）
//                Trigger trigger = TriggerBuilder.newTrigger()
//                        .withIdentity("trigger1","trigger_group1")
//                        .startNow()//马上启动
//                        .withSchedule( //链式编程
//                                SimpleScheduleBuilder
//                                        .simpleSchedule()
//                                        .repeatSecondlyForever(5000))//每5秒重复执行trigger
//                        .build();//实例化JobDetailImpl实例
//                //让调度器调度任务和触发器，保证按照触发器定义的条件执行任务
//                scheduler.scheduleJob(jobDetail, trigger);
//                //启动调度器
//                scheduler.start();
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }

        }
    //回调方法，当收到消息后，会自动执行该方法
}
