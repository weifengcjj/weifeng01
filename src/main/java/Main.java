import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class Main {
    public static void main(String[] args) throws IOException, TimeoutException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入邮箱");
        String em=scanner.next();
        Model model=new Model(em);
        Email email=new Email(model);
        String body=Json.jsonString(model);
        ProducerTe.pro(body);
        new ConsumerTe().run();
        email.run();
    }
}
