import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.*;
import org.quartz.simpl.PropertySettingJobFactory;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class View {
    public static void main(String[] args) {
        try {
            //1.调度器（Scheduler）,从工厂中获取调度的实例 （默认：实例化 new StdSchedulerFactory(); ）
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //2.任务实例（JobDetail）
            JobDetail jobDetail = JobBuilder.newJob(Myjob.class)     //加载任务类，与HelloJob完成绑定，要求HelloJob实现Job接口
                    .withIdentity("job1", "group1")   //参数1：任务的名称(唯一实例) 参数2：任务组的名称
                    .build();
            //3.触发器（Trigger）
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1","trigger_group1")
                    .startNow()//马上启动
                    .withSchedule( //链式编程
                            SimpleScheduleBuilder
                                    .simpleSchedule()
                                    .repeatSecondlyForever(5000))//每5秒重复执行trigger
                    .build();//实例化JobDetailImpl实例
            //让调度器调度任务和触发器，保证按照触发器定义的条件执行任务
            scheduler.scheduleJob(jobDetail, trigger);
            //启动调度器
            scheduler.start();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
