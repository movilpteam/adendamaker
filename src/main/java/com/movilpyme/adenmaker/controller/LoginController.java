package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Login;
import com.movilpyme.adenmaker.domain.PasswordConfig;
import com.movilpyme.adenmaker.domain.PreguntasPwd;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.*;
import com.movilpyme.adenmaker.security.TokenHelper;
import com.movilpyme.adenmaker.utils.Constantes;
import com.movilpyme.adenmaker.utils.PwdGenerator;
import com.movilpyme.adenmaker.utils.SendMail;
import com.movilpyme.adenmaker.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("login")
public class LoginController {

    private final UsuariosRepo usuariosRepo;
    private final RolesRepo rolesRepo;
    private final UserRolesRepo userRolesRepo;
    private final LoginRepo loginRepo;
    private final EmailRepo emailRepo;
    private final CorreoPlantillaRepo plantillaRepo;
    private final PasswordConfigRepo passwordConfigRepo;
    private final PreguntasRepo preguntasRepo;
    private final TokenHelper tokenHelper;

    private SendMail sendMail;

    @Autowired
    public LoginController(UsuariosRepo usuariosRepo,
                           RolesRepo rolesRepo,
                           UserRolesRepo userRolesRepo,
                           LoginRepo loginRepo,
                           EmailRepo emailRepo,
                           CorreoPlantillaRepo plantillaRepo,
                           TokenHelper tokenHelper,
                           PasswordConfigRepo passwordConfigRepo,
                           PreguntasRepo preguntasRepo) {
        this.usuariosRepo = usuariosRepo;
        this.rolesRepo = rolesRepo;
        this.userRolesRepo = userRolesRepo;
        this.loginRepo = loginRepo;
        this.emailRepo = emailRepo;
        this.plantillaRepo = plantillaRepo;
        this.tokenHelper = tokenHelper;
        this.passwordConfigRepo = passwordConfigRepo;
        this.preguntasRepo = preguntasRepo;
    }

    @RequestMapping(value = "resetPwd", method = RequestMethod.POST)
    public boolean resetPassword(@RequestBody Map<String, String> requestData) throws ServletException {
        if (requestData.get("username") == null){
            throw new ServletException("No proporciono nombre de usuario");
        }
        Usuarios user = usuariosRepo.findByUsername(requestData.get("username"));
        if (user != null){
            if (requestData.get("answer") != null && !requestData.get("answer").equalsIgnoreCase("NA")){
                if (!user.getRespuestaSecreta().equalsIgnoreCase(requestData.get("answer"))){
                    return true;
                }
            }
            try {
                sendMail = new SendMail(emailRepo, plantillaRepo);
                List<PasswordConfig> configList = passwordConfigRepo.findAllByName(Constantes.LENGTH);
                int pwd_size = 8;
                if (configList != null && configList.size() > 0){
                    pwd_size = Integer.valueOf(configList.get(0).getValor());
                }
                String str_pwd = PwdGenerator.generatePassword(pwd_size);
                String hash_pwd = PwdGenerator.passwordSHA512(str_pwd);
                String[] to = new String[1];
                to[0] = user.getCorreo();
                user.setPassword(BCrypt.hashpw(hash_pwd, BCrypt.gensalt()));
                user.setCambiarPwd(true);
                usuariosRepo.save(user);
                sendMail.sendTemplateEmail(str_pwd, user.getUsername(), to, "reset");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    @RequestMapping(value = "credentials", method = RequestMethod.POST)
    public Login checkCredentials(@RequestBody Map<String, String> requestData) throws ServletException {
        if (requestData.get("username") == null || requestData.get("pwd") == null){
            throw new ServletException("Información Incompleta");
        }
        Usuarios user = usuariosRepo.findByUsername(requestData.get("username"));
        if (user == null){
            throw new ServletException("Usuario o password Inválido");
        }
        if (!BCrypt.checkpw(requestData.get("pwd"), user.getPassword())){
            throw new ServletException("Usuario o contraseña Incorrecta");
        }
        if (loginRepo.findAllByUsuarioAndActivaTrue(user).size() > 0){
            throw new ServletException("Ya existe una sesion activa. Cierre la sesion o contacte al Administrador");
        }
        try {
            Login login = new Login();
            login.setUsuario(user);
            login.setToken(tokenHelper.generateToken(user.getUsername()));
            login.setFecha_inicio(new Date());
            login.setActiva(true);
            if (!user.getCambiarPwd()){
                loginRepo.save(login);
            }
            return login;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "autoreset", method = RequestMethod.POST)
    public boolean getAutoReset() throws ServletException {
        List<PasswordConfig> list = passwordConfigRepo.findAllByName(Constantes.AUTORENEW);
        if (list != null && list.size() > 0) {
            return list.get(0).getValor() != null;
        }
        return false;
    }

    @RequestMapping(value = "pwdregex", method = RequestMethod.POST)
    public Map<String, String> getPwdRegex() throws ServletException {
        List<PasswordConfig> configList = (List<PasswordConfig>) passwordConfigRepo.findAll();
        if (configList == null || configList.size() == 0){
            throw new ServletException("Configuracion Password Default");
        }
        Map<String, String> response = new HashMap<>();
        response.put("regex", Utils.getPwdRegex(configList));
        return response;
    }

    @RequestMapping(value = "preguntas", method = RequestMethod.POST)
    public List<PreguntasPwd> getSecretQuestion() throws ServletException {
        List<PreguntasPwd> list = (List<PreguntasPwd>) preguntasRepo.findAll();
        return list;
    }

    @RequestMapping(value = "user/pregunta", method = RequestMethod.POST)
    public Map<String, String> getQuestionByUser(@RequestBody Map<String, String> requestData) throws ServletException {
        if (requestData.get("username") == null){
            throw new ServletException("Usuario Inválido ó Pregunta Inválida");
        }
        Usuarios user = usuariosRepo.findByUsername(requestData.get("username"));
        if (user == null){
            throw new ServletException("Usuario Inválido");
        }
        Map<String, String> responseData = new HashMap<>();
        if (user.getPreguntaSecreta() == 0){
            responseData.put("NA", "true");
            return responseData;
        }
        PreguntasPwd pregunta = preguntasRepo.findOne(user.getPreguntaSecreta());
        responseData.put("NA", "false");
        responseData.put("pregunta", pregunta.getPregunta());
        return responseData;
    }
}
