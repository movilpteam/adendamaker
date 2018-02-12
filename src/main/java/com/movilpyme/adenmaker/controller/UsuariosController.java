package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("adm/usuarios")
public class UsuariosController {

    private final UsuariosRepo usuariosRepo;

    @Autowired
    public UsuariosController(UsuariosRepo usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<Usuarios> getUsuariosList() throws ServletException {
        List<Usuarios> usuariosList = (List<Usuarios>) usuariosRepo.findAll();
        return usuariosList;
    }
}
