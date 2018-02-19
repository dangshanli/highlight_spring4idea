package common_toolkits.twent_useful_segments;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author: luzj
 * @date:2018/2/12
 * @description: 继承MailService类，重写Message包装类，
 */
public class SimpleMail extends MailService {

    /**
     * 传输普通文件
     * @param message
     * @return
     * @throws MessagingException
     */
    @Override
    public MimeMessage fillMessage(MimeMessage message) throws MessagingException {
        String title = "二狗！！！";
        String content = "行动如风轻盈似箭";
        message.setText(content);
        message.setSubject(title);
        message.setSentDate(new Date());

        return message;
    }

    public static void main(String[] args) {
        String userName = "18326916629@163.com",
                password = "12qwer",
                recipient = "1414377646@qq.com";
        try {
            new SimpleMail().send(userName,password,recipient);
        } catch (MessagingException e) {
            System.err.println("邮件发送出现异常");
            e.printStackTrace();
        }finally {
            System.out.println("结束了");
        }

    }
}
