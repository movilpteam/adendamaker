package com.movilpyme.adenmaker.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

    private final JavaMailSenderImpl mailSender;

    @Autowired
    public SendMail(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetPwdEmail(String subject, String newPwd, String username, String[] to) throws Exception {
        String message = "Ha solicitado la creacion de una nueva contraseña para ingresar a AddendaMaker";
        message = message + "<br><br>Su usuario para ingresar es: <strong>" + username + "</strong>";
        message = message + "<br>Su clave temporal de acceso es: <strong>" + newPwd + "</strong>";
        prepareAndSendMessage(message, subject, to);
    }

    public void sendWelcomeEmail(String subject, String newPwd, String username, String[] to) throws Exception {
        String message = "Bienvenido al sistema de creacion de Addendas AddendaMaker";
        message = message + "<br><br>Su usuario para ingresar es: <strong>" + username + "</strong>";
        message = message + "<br>Su clave temporal de acceso es: <strong>" + newPwd + "</strong>";
        prepareAndSendMessage(message, subject, to);
    }

    public void prepareAndSendMessage(String message, String subject, String[] to) throws Exception {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mailSender.getUsername());
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
        };
        try{
            mailSender.send(messagePreparator);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
