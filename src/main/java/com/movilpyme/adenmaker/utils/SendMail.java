package com.movilpyme.adenmaker.utils;

import com.movilpyme.adenmaker.bean.StpSelCursorRes;
import com.movilpyme.adenmaker.domain.Correo;
import com.movilpyme.adenmaker.domain.CorreoPlantilla;
import com.movilpyme.adenmaker.persistence.dao.ApplicationBuroDao;
import com.movilpyme.adenmaker.persistence.dao.impl.ApplicationBuroDaoImpl;
import com.movilpyme.adenmaker.repository.CorreoPlantillaRepo;
import com.movilpyme.adenmaker.repository.EmailRepo;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class SendMail {

    private JavaMailSenderImpl mailSender;
    private final ApplicationBuroDaoImpl buroDao;
    private final EmailRepo emailRepo;
    private final CorreoPlantillaRepo plantillaRepo;
    private Long id_config;

    @Autowired
    public SendMail(ApplicationBuroDaoImpl buroDao, EmailRepo emailRepo, CorreoPlantillaRepo plantillaRepo) throws Exception {
        this.buroDao = buroDao;
        this.emailRepo = emailRepo;
        this.plantillaRepo = plantillaRepo;
        this.id_config = 1L;
        prepareParams();
    }

    public void prepareParams() throws Exception{
        mailSender = new JavaMailSenderImpl();
        Properties props = new Properties();
        List<Correo> configs = (List<Correo>) emailRepo.findAll();
        if (configs.size() == 0){
            throw new Exception("No hay configuracion de correo");
        }
        Correo correo = configs.get(0);
        mailSender.setPassword(correo.getPwd());
        mailSender.setHost(correo.getEndServer());
        mailSender.setUsername(correo.getNameUser());
        mailSender.setPort(Integer.valueOf(correo.getEntrancePort()));
        if (correo.isCertificate()){
            props.put("mail.smtps.auth", "true");
            props.setProperty("mail.smtp.ssl.enable", "true");
            props.put("mail.smtps.ssl.trust", "*");
            mailSender.setProtocol("smtps");
        }else {
            props.put("mail.smtp.auth", "true");
            mailSender.setProtocol("smtp");
        }
        mailSender.setJavaMailProperties(props);
        id_config = correo.getId();
    }

    public void sendResetPwdEmail(String subject, String newPwd, String username, String[] to) throws Exception {
        String message = "Ha solicitado la creacion de una nueva contraseña para ingresar a AddendaMaker";
        message = message + "<br><br>Su usuario para ingresar es: <strong>" + username + "</strong>";
        message = message + "<br>Su clave temporal de acceso es: <strong>" + newPwd + "</strong>";
        prepareAndSendMessage(message, subject, to);
    }

    public void sendWelcomeEmail(String subject, String newPwd, String username, String[] to) throws Exception {
        /*String message = "Bienvenido al sistema de creacion de Addendas AddendaMaker";
        message = message + "<br><br>Su usuario para ingresar es: <strong>" + username + "</strong>";
        message = message + "<br>Su clave temporal de acceso es: <strong>" + newPwd + "</strong>";*/

        List<CorreoPlantilla> plantillaList = plantillaRepo.findAllByNombre("welcome");
        if (plantillaList.size() == 0){
            throw new Exception("No existe plantilla configurada");
        }
        CorreoPlantilla plantilla = plantillaList.get(0);
        String message = plantilla.getBody();
        message = message.replace("{username}", username);
        message = message.replace("{pwd}", newPwd);
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
