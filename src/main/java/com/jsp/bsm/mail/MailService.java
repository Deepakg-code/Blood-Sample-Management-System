package com.jsp.bsm.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Map;


@Service
@AllArgsConstructor
public class MailService {

    private   TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;


    public void sendMail(String to,String subject,String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);//we cannot change directly into the mimeMessage so we use helper class
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(text,true);
        mimeMessageHelper.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }

    public  String generateContent(String templateName, Map<String,Object> variables){
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName,context);
    }

}