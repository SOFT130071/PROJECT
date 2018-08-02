package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailService {
    private Session session;
    private final String d_email = "soft130071@outlook.com";
    private final String d_password = "asdfghjkl;'";

    MailService() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        try {
            Authenticator auth = new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(d_email, d_password);
                }
            };

            session = Session.getInstance(props, auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized int sendMsg(String addr, String subject, String content) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(d_email));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(content, "text/html;charset=UTF-8");
            msg.setSentDate(new Date());

            Transport.send(msg, new Address[]{new InternetAddress(addr)});

            return 0x010300;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0x010301;
    }
}
