package common_toolkits.twent_useful_segments;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.net.ssl.internal.ssl.Provider;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: 使用java写发送邮件的类
 * @date 2018/2/8 16:03
 */
public abstract class MailService {

    /**
     * 使用网易邮箱 发送给QQ邮箱邮件
     *
     * @param username       18326916629@163.com
     * @param password       12qwer
     * @param recipientEmail 1414377646@qq.com
     * @throws MessagingException
     */
    public void send(final String username, final String password, String recipientEmail)
            throws MessagingException {
        this.sendSimpleMail(username, password, recipientEmail, "smtp.163.com", "465", "");
    }

    /**
     * 发送邮件工具类，走SMPT协议,发送简单地文本信息
     *
     * @param userName       发送者邮箱用户名
     * @param password       发送者密码
     * @param recipientEmail 接收者邮箱
     * @param ccEmail        CC接收者，可为空
     * @throws MessagingException if the connection is dead or not in the connected state
     *                            or if the message is not a MimeMessage
     */
    public void sendSimpleMail(final String userName,
                               final String password,
                               String recipientEmail,
                               String host,
                               String port,
                               String ccEmail

    ) throws MessagingException {

        MessageAndSession messageAndSession = wrapMessage(userName, recipientEmail, host, port, ccEmail);
        Session session = messageAndSession.getSession();
        MimeMessage msg = messageAndSession.getMessage();

        //todo 设置发送信息、时间等
      /*  msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());*/
//        msg = this.fillMessage(msg);

        //todo 启用SMTP协议建立连接，要求发送邮箱的登录用户名和授权码
        transMsgBySMPT(host, userName, password, session, this.fillMessage(msg));
    }

    /**
     * 通过实现不同信息类，传达不同信息 普通文本==网页==附件等
     *
     * @param message
     * @return
     * @throws MessagingException
     */
    public abstract MimeMessage fillMessage(MimeMessage message) throws MessagingException;

    /**
     * 使用SMPT协议发送message给目标邮箱
     *
     * @param host     主机地址
     * @param userName 邮箱用户名
     * @param password 邮箱授权码
     * @return
     */
    private static void transMsgBySMPT(String host,
                                       String userName,
                                       String password,
                                       Session session,
                                       MimeMessage msg) throws MessagingException {
        //todo 启用SMTP协议建立连接，要求发送邮箱的登录用户名和授权码
        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        t.connect(host, userName, password);
//        t.connect("smtp.163.com","18326916629@163.com","12qwer");
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }


    /**
     * @param userName       183...@163.com
     * @param recipientEmail 1414...
     * @param host           smtp.163.com
     * @param port           465
     * @param ccEmail
     * @return
     * @throws MessagingException
     */
    private static MessageAndSession wrapMessage(String userName,
                                                 String recipientEmail,
                                                 String host,
                                                 String port,
                                                 String ccEmail) throws MessagingException {
        MessageAndSession messageAndSession = new MessageAndSession();
        //todo class : com.sun.net.ssl.internal.ssl.Provider
        Security.addProvider(new Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        //todo 设置邮件发送属性properties
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", host);//邮箱服务器地址
        props.setProperty("mail.smtp.port", port);//服务端口号
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);//SSl安全工厂类
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", port);
        props.setProperty("mail.smtps.auth", "true");//开启权限
        /**
         * 如果设置false，一个quit命令就关闭连接，传输还要等待服务端对quit的响应信息
         */
        props.put("mail.smtps.quitwait", "false");
        //todo 根据properties属性创建会话
        Session session = Session.getDefaultInstance(props, null);

        //todo 包装信息类Message,他包装了 ：发送者、接收者、发送信息、发送时间、会话信息Session
        final MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        //recipientEmail 是一个可以用逗号分割的接收地址列表
        message.addRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail, false));

        if (ccEmail != null && ccEmail.length() > 0) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }
        messageAndSession.setMessage(message);
        messageAndSession.setSession(session);
        return messageAndSession;

    }

}
