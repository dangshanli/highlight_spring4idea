package common_toolkits.twent_useful_segments;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * Created by MyPC on 2018/2/11.
 */
public class MessageAndSession {
    Session session;
    MimeMessage message;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public MimeMessage getMessage() {
        return message;
    }

    public void setMessage(MimeMessage message) {
        this.message = message;
    }
}
