package com.movilpyme.adenmaker.utils;

import com.movilpyme.adenmaker.domain.Correo;
import com.movilpyme.adenmaker.domain.CorreoPlantilla;
import com.movilpyme.adenmaker.repository.CorreoPlantillaRepo;
import com.movilpyme.adenmaker.repository.EmailRepo;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.util.List;
import java.util.Properties;
public class SendMail {

    private JavaMailSenderImpl mailSender;
    private final EmailRepo emailRepo;
    private final CorreoPlantillaRepo plantillaRepo;
    private Correo config;

    public SendMail(EmailRepo emailRepo, CorreoPlantillaRepo plantillaRepo) throws Exception {
        this.emailRepo = emailRepo;
        this.plantillaRepo = plantillaRepo;
        prepareParams();
    }

    public void prepareParams() throws Exception{
        mailSender = new JavaMailSenderImpl();
        Properties props = new Properties();
        List<Correo> configs = (List<Correo>) emailRepo.findAll();
        if (configs.size() == 0){
            throw new Exception("No hay configuracion de correo");
        }
        config = configs.get(0);
        mailSender.setPassword(config.getPwd());
        mailSender.setHost(config.getEndServer());
        mailSender.setUsername(config.getNameUser());
        mailSender.setPort(Integer.valueOf(config.getEntrancePort()));
        if (config.isCertificate()){
            props.put("mail.smtps.auth", "true");
            props.setProperty("mail.smtp.ssl.enable", "true");
            props.put("mail.smtps.ssl.trust", "*");
            mailSender.setProtocol("smtps");
            props.setProperty("mail.smtps.allow8bitmime", "true");
        }else {
            props.put("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.allow8bitmime", "true");
            mailSender.setProtocol("smtp");
        }
        mailSender.setJavaMailProperties(props);
    }

    public void sendResetPwdEmail(String subject, String newPwd, String username, String[] to) throws Exception {
        String message = "Ha solicitado la creacion de una nueva contraseña para ingresar a AddendaMaker";
        message = message + "<br><br>Su usuario para ingresar es: <strong>" + username + "</strong>";
        message = message + "<br>Su clave temporal de acceso es: <strong>" + newPwd + "</strong>";
        prepareAndSendMessage(message, subject, to);
    }

    public void sendTemplateEmail(String newPwd, String username, String[] to, String template_name) throws Exception {
        CorreoPlantilla plantilla = plantillaRepo.findByCorreoByIdCorreoAndNombre(config, template_name);
        if (plantilla == null){
            throw new Exception("No existe plantilla configurada");
        }
        String message = plantilla.getBody();
        message = message.replace("{username}", username);
        message = message.replace("{password}", newPwd);
        prepareAndSendMessage(message, plantilla.getAsunto(), to);
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
