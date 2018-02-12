package common_toolkits.twent_useful_segments;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;


/**
 * 失败
 */

/**
 * @author: luzj
 * @date:2018/2/12
 * @description: 发送Html
 */
@Deprecated
public class HtmlMail extends MailService {

    /**
     * 貌似不好使
     * @param message
     * @return
     * @throws MessagingException
     */
    @Override
    public MimeMessage fillMessage(MimeMessage message) throws MessagingException {
        String title = "html 级别的消息？？？";
        String content = "<h1>求求你，打个折吧！！！</h1></br>" +
                "<p>求求你送我点京东卡券吧！！！</p></br>";
        message.setSubject(title);
        message.setContent(content, "text/html");
        message.setSentDate(new Date());
        return message;
    }

    public static void main(String[] args) {
        String userName = "18326916629@163.com",
                password = "12qwer",
                recipient = "1414377646@qq.com";
        try {
            new HtmlMail().send(userName, password, recipient);
        } catch (MessagingException e) {
            System.err.println("邮件发送出现异常");
            e.printStackTrace();
        } finally {
            System.out.println("结束了!!!!");
        }
    }
}
