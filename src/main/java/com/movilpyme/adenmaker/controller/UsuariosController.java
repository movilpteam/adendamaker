package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Roles;
import com.movilpyme.adenmaker.domain.UserRoles;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.RolesRepo;
import com.movilpyme.adenmaker.repository.UserRolesRepo;
import com.movilpyme.adenmaker.repository.UsuariosRepo;
import com.movilpyme.adenmaker.utils.PwdGenerator;
import com.movilpyme.adenmaker.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("adm/usuarios")
public class UsuariosController {

    private final UsuariosRepo usuariosRepo;
    private final RolesRepo rolesRepo;
    private final UserRolesRepo userRolesRepo;
    private final SendMail sendMail;

    @Autowired
    public UsuariosController(UsuariosRepo usuariosRepo, RolesRepo rolesRepo, UserRolesRepo userRolesRepo, SendMail sendMail) {
        this.usuariosRepo = usuariosRepo;
        this.rolesRepo = rolesRepo;
        this.userRolesRepo = userRolesRepo;
        this.sendMail = sendMail;
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
                String hash_pwd = BCrypt.hashpw(str_pwd, BCrypt.gensalt());
                String username = user.getNombre().substring(0,1) + user.getApaterno();
                if (usuariosRepo.countByUsername(username.toLowerCase()) > 0){
                    username = user.getNombre().substring(0,1) + user.getApaterno().substring(0,2) + user.getAmaterno().substring(0,2);
                }
                String[] to = new String[1];
                to[0] = user.getCorreo();
                sendMail.sendWelcomeEmail("Bienvenido a AddenMaker", str_pwd, username.toLowerCase(), to);
                user.setUsername(username.toLowerCase());
                user.setPassword(hash_pwd);
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
}
