package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Login;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.*;
import com.movilpyme.adenmaker.security.TokenHelper;
import com.movilpyme.adenmaker.utils.PwdGenerator;
import com.movilpyme.adenmaker.utils.SendMail;
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
    private final TokenHelper tokenHelper;

    private SendMail sendMail;

    @Autowired
    public LoginController(UsuariosRepo usuariosRepo, RolesRepo rolesRepo, UserRolesRepo userRolesRepo, LoginRepo loginRepo, EmailRepo emailRepo, CorreoPlantillaRepo plantillaRepo, TokenHelper tokenHelper) {
        this.usuariosRepo = usuariosRepo;
        this.rolesRepo = rolesRepo;
        this.userRolesRepo = userRolesRepo;
        this.loginRepo = loginRepo;
        this.emailRepo = emailRepo;
        this.plantillaRepo = plantillaRepo;
        this.tokenHelper = tokenHelper;
    }

    @RequestMapping(value = "resetPwd", method = RequestMethod.POST)
    public boolean resetPassword(@RequestBody Map<String, String> requestData) throws ServletException {
        if (requestData.get("username") == null){
            throw new ServletException("No proporciono nombre de usuario");
        }
        Usuarios user = usuariosRepo.findByUsername(requestData.get("username"));
        if (user != null){
            try {
                sendMail = new SendMail(emailRepo, plantillaRepo);
                String str_pwd = PwdGenerator.generatePassword(8);
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
}
