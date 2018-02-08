package common_toolkits.twent_useful_segments;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.net.ssl.internal.ssl.Provider;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: 使用java写发送邮件的类
 * @date 2018/2/8 16:03
 */
public class MailService {

    /**
     * 使用网易邮箱 发送给QQ邮箱邮件
     *
     * @param username       18326916629
     * @param password       milanda...
     * @param recipientEmail 1414377646@qq.com
     * @param title          你好啊
     * @param message        好久不见，我亲爱的朋友！！！
     * @throws MessagingException
     */
    public static void send(final String username, final String password, String recipientEmail, String title, String message)
            throws MessagingException {
        MailService.sendSimpleMail(username, password, recipientEmail, "", title, message);
    }

    /**
     * 发送邮件工具类，走SMPT协议,发送简单地文本信息
     *
     * @param userName       发送者邮箱用户名
     * @param password       发送者密码
     * @param recipientEmail 接收者邮箱
     * @param ccEmail        CC接收者，可为空
     * @param title          邮件信息标题
     * @param message        邮件信息体
     * @throws MessagingException if the connection is dead or not in the connected state
     *                            or if the message is not a MimeMessage
     */
    public static void sendSimpleMail(final String userName,
                                      final String password,
                                      String recipientEmail,
                                      String ccEmail,
                                      String title,
                                      String message

    ) throws MessagingException {
        //todo class : com.sun.net.ssl.internal.ssl.Provider
        Security.addProvider(new Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        //todo 设置邮件发送属性properties
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.163.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        /**
         * 如果设置false，一个quit命令就关闭连接，传输还要等待服务端对quit的响应信息
         */
        props.put("mail.smtps.quitwait", "false");
        //todo 根据properties属性创建会话
        Session session = Session.getDefaultInstance(props, null);

        //todo 包装信息类Message,他包装了 ：发送者、接收者、发送信息、发送时间、会话信息Session
        final MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName + "@163.com"));
        msg.addRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail, false));

        if (ccEmail != null && ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.163.com", userName+"@163.com", "12qwer");
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

    public static void main(String[] args) {
        String userName = "18326916629",
                password = "miland...",
                recipient = "1414377646@qq.com",
                title = "你好啊",
                message = "好久不见，我亲爱的朋友！！！";


        try {
            MailService.send(userName,password,recipient,title,message);
        } catch (MessagingException e) {
            System.err.println("ops...");
            e.printStackTrace();
        }finally {
            System.out.println("发送完毕，请查收"+recipient+"邮箱");
        }
    }


}
