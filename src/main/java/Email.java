

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class Email implements Runnable {
            //登录qq邮箱服务器
            private Model model;
            public String receiveMailAccount;
            public Email(Model model)
            {
                this.receiveMailAccount=model.getEmail();
            }
            @Override
            public void run() {
                //发件人的邮箱 密码
                //发件人邮箱的SMTP
                // 收件人邮箱

                Properties properties = new Properties();//参数配置
                properties.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
                properties.setProperty("mail.smtp.host", "smtp.qq.com");//发件人的邮箱的 SMTP 服务器地址
                properties.setProperty("mail.smtp.auth", "true");// 需要请求认证

                //根据配置创建会话对象, 用于和邮件服务器交互
                Session session = Session.getInstance(properties);
                // 设置为debug模式, 可以查看详细的发送log
                session.setDebug(true);

                //创建邮件
                MimeMessage message = createMimeMessage(session, "smtp.qq.com", receiveMailAccount);
                Transport transport = null;
                try {
                    //根据 Session 获取邮件传输对象
                    transport = session.getTransport();
                    //使用邮箱账号和密码连接邮件服务器
                    transport.connect("3448589962@qq.com", "caavteyqffpydabi");
                    message.setFrom(new InternetAddress("3448589962@qq.com"));
                    //发送邮件
                    transport.sendMessage(message, message.getAllRecipients());
                    //关闭连接
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                } finally {
                    if (transport != null) {
                        try {
                            transport.close();//关闭连接
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) {
        //创建一封邮件
        MimeMessage message = new MimeMessage(session);
        try {
            //发件人
            message.setFrom(new InternetAddress(sendMail));

            //收件人
            message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMail));

            //Subject: 邮件主题
            message.setSubject("小实验", "UTF-8");

            //Content: 邮件正文
            message.setContent("蟹bro是真帅！!", "text/html;charset=UTF-8");
            //设置发件时间
            message.setSentDate(new Date());

            //保存设置
            message.saveChanges();


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
