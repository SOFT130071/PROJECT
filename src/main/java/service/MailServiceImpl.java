package service;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailServiceImpl implements MailService {
    private Transport ts;
    private Session session;
    private final String sender = "soft130071";

    MailServiceImpl() {
        try {
            //TODO: 此处应该是配置文件但是我懒
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.host", "smtp.outlook.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");

            session = Session.getInstance(prop);
            session.setDebug(true);
            ts = session.getTransport();
            ts.connect("smtp.outlook.com", sender, "asdfghjkl;'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized int sendMsg(InternetAddress addr, String subject, String content) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender + "@outlook.com"));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(content, "text/html;charset=UTF-8");
            msg.setSentDate(new Date());

            ts.sendMessage(msg, new Address[]{addr});
            ts.close();
            return 0x010300;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0x010301;
    }
}
