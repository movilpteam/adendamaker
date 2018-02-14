package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Roles;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.RolesRepo;
import com.movilpyme.adenmaker.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("adm/usuarios")
public class UsuariosController {

    private final UsuariosRepo usuariosRepo;
    private final RolesRepo rolesRepo;

    @Autowired
    public UsuariosController(UsuariosRepo usuariosRepo, RolesRepo rolesRepo) {
        this.usuariosRepo = usuariosRepo;
        this.rolesRepo = rolesRepo;
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
    public void saveUsuario(@RequestBody Usuarios user) throws ServletException {
        if (user == null){
            throw new ServletException("Usuarios Invalido");
        }
        try {
            usuariosRepo.save(user);
        }catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }
    }
}
