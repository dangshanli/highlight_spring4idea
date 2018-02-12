package common_toolkits.twent_useful_segments;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author: luzj
 * @date:2018/2/12
 * @description: 发送文件,邮件带附件
 */
public class FileMail extends MailService {
    /**
     * 包装袋附件的Message类
     * @param message
     * @return
     * @throws MessagingException
     */
    @Override
    public MimeMessage fillMessage(MimeMessage message) throws MessagingException {
        // Set Subject: header field
        message.setSubject("This is the Subject Line!");

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        messageBodyPart.setText("This is message body");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = "E:\\壁纸\\m.jpg";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("我的壁纸");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);
        return message;
    }

    public static void main(String[] args) {
        String userName = "18326916629@163.com",
                password = "12qwer",
                recipient = "1414377646@qq.com";
        try {
            new FileMail().send(userName, password, recipient);
        } catch (MessagingException e) {
            System.err.println("邮件发送出现异常");
            e.printStackTrace();
        } finally {
            System.out.println("结束了!!!!");
        }
    }
}
