package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Login;
import com.movilpyme.adenmaker.domain.Roles;
import com.movilpyme.adenmaker.domain.UserRoles;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.LoginRepo;
import com.movilpyme.adenmaker.repository.RolesRepo;
import com.movilpyme.adenmaker.repository.UserRolesRepo;
import com.movilpyme.adenmaker.repository.UsuariosRepo;
import com.movilpyme.adenmaker.utils.PwdGenerator;
import com.movilpyme.adenmaker.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("adm/usuarios")
public class UsuariosController {

    private final UsuariosRepo usuariosRepo;
    private final RolesRepo rolesRepo;
    private final UserRolesRepo userRolesRepo;
    private final LoginRepo loginRepo;
    private final SendMail sendMail;

    @Autowired
    public UsuariosController(UsuariosRepo usuariosRepo, RolesRepo rolesRepo, UserRolesRepo userRolesRepo, LoginRepo loginRepo, SendMail sendMail) {
        this.usuariosRepo = usuariosRepo;
        this.rolesRepo = rolesRepo;
        this.userRolesRepo = userRolesRepo;
        this.loginRepo = loginRepo;
        this.sendMail = sendMail;
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public boolean changePassword(@RequestBody Map<String, String> requestData) throws ServletException {
        if (requestData.get("username") == null || requestData.get("actual") == null || requestData.get("nuevo") == null){
            throw new ServletException("Información Inválida");
        }
        Usuarios user = usuariosRepo.findByUsername(requestData.get("username"));
        if (user == null){
            throw new ServletException("Información Inválida");
        }
        if (!BCrypt.checkpw(requestData.get("actual"), user.getPassword())){
            throw new ServletException("Información Inválida");
        }
        try {
            user.setPassword(BCrypt.hashpw(requestData.get("nuevo"), BCrypt.gensalt()));
            user.setCambiarPwd(requestData.get("changePwd") != null);
            usuariosRepo.save(user);
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "roles/{iduser}", method = RequestMethod.POST)
    public List<UserRoles> getRolesByUser(@PathVariable Long iduser) throws ServletException {
        if (iduser == 0){
            throw new ServletException("Usuarios Inválido");
        }
        return userRolesRepo.findByIdUser(iduser);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<Usuarios> getUsuariosList() throws ServletException {
        List<Usuarios> usuariosList = (List<Usuarios>) usuariosRepo.findAll();
        return usuariosList;
    }

    @RequestMapping(value = "roles/list", method = RequestMethod.POST)
    public List<Roles> getRolesList() throws ServletException {
        List<Roles> rolesList = (List<Roles>) rolesRepo.findAll();
        return rolesList;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public boolean saveUsuario(@RequestBody Usuarios user) throws ServletException {
        if (user == null){
            throw new ServletException("Usuarios Invalido");
        }
        try {
            if (user.getId() == 0){
                String str_pwd = PwdGenerator.generatePassword(8);
                String hash_pwd = PwdGenerator.passwordSHA512(str_pwd);
                String username = user.getNombre().substring(0,1) + user.getApaterno();
                if (usuariosRepo.countByUsername(username.toLowerCase()) > 0){
                    username = user.getNombre().substring(0,1) + user.getApaterno().substring(0,2) + user.getAmaterno().substring(0,2);
                }
                String[] to = new String[1];
                to[0] = user.getCorreo();
                sendMail.sendWelcomeEmail("Bienvenido a AddenMaker", str_pwd, username.toLowerCase(), to);
                user.setUsername(username.toLowerCase());
                user.setPassword(BCrypt.hashpw(hash_pwd, BCrypt.gensalt()));
                user.setEnabled(true);
                user.setCambiarPwd(true);
                user.setLastPwdChg(new Date());
            }
            List<UserRoles> userRolesList = new ArrayList(user.getUserRolesById());
            Roles role = rolesRepo.findOne(userRolesList.get(0).getIdRole());
            usuariosRepo.save(user);
            UserRoles userRoles = new UserRoles();
            userRoles.setRolesByIdRole(role);
            userRoles.setUsuariosByIdUser(user);
            userRoles.setIdRole(role.getId());
            userRoles.setIdUser(user.getId());
            userRolesRepo.deleteByIdUser(user.getId());
            userRolesRepo.save(userRoles);
            return true;
        }catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public boolean logout(@RequestBody Login login) throws ServletException {
        if (login == null){
            throw new ServletException("Login Invalido");
        }
        Login l = loginRepo.findOne(login.getId());
        l.setBeat(new Date());
        l.setFecha_final(new Date());
        l.setLogout_comments("LogOut Correcto Solicitado por Usuario");
        l.setActiva(false);
        loginRepo.save(l);
        return true;
    }
}
