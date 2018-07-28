package service;

import javax.mail.internet.InternetAddress;

public interface MailService {
    int sendMsg(InternetAddress addr, String subject, String content);
}
