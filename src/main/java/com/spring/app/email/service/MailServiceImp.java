package com.spring.app.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.spring.app.email.models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImp implements MailService
{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            mimeMessageHelper.setTo(mail.getMailTo());

            MimeMessage message = mimeMessageHelper.getMimeMessage();
            message.setContent(mail.getMailContent(), "text/html");
            
            javaMailSender.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
