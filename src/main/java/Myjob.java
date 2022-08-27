import com.rabbitmq.client.Consumer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class Myjob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        new Thread(new ConsumerTe()).start();
        System.out.println("当前时间为：" + dateFormat.format(date));
    }
    }
