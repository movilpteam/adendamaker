package com.movilpyme.adenmaker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import com.movilpyme.adenmaker.domain.Roles;
import com.movilpyme.adenmaker.domain.UserRoles;
import com.movilpyme.adenmaker.domain.Usuarios;
import com.movilpyme.adenmaker.repository.UserRolesRepo;
import com.movilpyme.adenmaker.repository.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.domain.Empresa;
import com.movilpyme.adenmaker.repository.EmpresaRepo;

@RestController
@RequestMapping("adm/empresa")
public class EmpresaController {

    private final EmpresaRepo empresaRepo;
    private final UserRolesRepo userRolesRepo;
    private final UsuariosRepo usuariosRepo;

    @Autowired
    public EmpresaController(EmpresaRepo empresaRepo, UserRolesRepo userRolesRepo, UsuariosRepo usuariosRepo) {
        this.empresaRepo = empresaRepo;
        this.userRolesRepo = userRolesRepo;
        this.usuariosRepo = usuariosRepo;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public boolean disableEmpresa(@PathVariable Long id) throws ServletException {
        if (id == 0){
            throw new ServletException("ID Inválido");
        }
        Empresa empresa = empresaRepo.findOne(id);
        if (empresa == null){
            throw new ServletException("No se encontro empresa");
        }
        empresa.setEnabled(false);
        empresaRepo.save(empresa);
        return true;
    }

    @RequestMapping(value = "byId/{id}", method = RequestMethod.POST)
    public Empresa getEmpresaById(@PathVariable Long id) throws ServletException {
        if (id == 0){
            throw new ServletException("ID Inválido");
        }
        Empresa empresa = empresaRepo.findOne(id);
        if (empresa == null){
            throw new ServletException("No se encontro empresa");
        }
        return empresa;
    }

    @RequestMapping(value = "list/{iduser}", method = RequestMethod.POST)
    public List<Empresa> getEmpresaList(@PathVariable long iduser) throws ServletException {
        if (iduser == 0){
            throw new ServletException("Usuario Invalido en Empresas");
        }
        List<UserRoles> rolesList = userRolesRepo.findByIdUser(iduser);
        if (rolesList == null || rolesList.size() == 0){
            throw new ServletException("Usuario no tiene configurado role");
        }
        List<Empresa> empresaList;
        if (rolesList.get(0).getIdRole() == 1 || rolesList.get(0).getIdRole() == 4){
            empresaList = empresaRepo.findAllByEnabledTrue();
        }else {
            Usuarios usuarios = usuariosRepo.findOne(iduser);
            empresaList = new ArrayList<>();
            empresaList.add(usuarios.getEmpresaByIdEmpresa());
        }
        return empresaList;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public boolean saveEmpresa(@RequestBody Empresa empresa) throws ServletException {
        if (empresa == null){
            throw new ServletException("Empresa Inválida");
        }
        try {
            empresaRepo.save(empresa);
            return true;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, String> updateEmpresa(@RequestBody Empresa empresa) throws ServletException {
        if (empresa == null){
            throw new ServletException("Empresa Inválida");
        }
        try {
        	Empresa empresaFind = empresaRepo.findOne(empresa.getId());
        	empresaFind.setLogo(empresa.getLogo());
        	empresaFind.setBodyLogo(empresa.getBodyLogo());
        	empresaRepo.save(empresaFind);
        	HashMap<String, String> responseData = new HashMap<>();
        	responseData.put("status", "1");
            responseData.put("bodyLogo", empresaFind.getBodyLogo());
            return responseData;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
    
    @RequestMapping(value = "viewLogo", method = RequestMethod.POST)
    public HashMap<String, String> viewLogo(@RequestBody Empresa empresa) throws ServletException {
        if (empresa == null){
            throw new ServletException("Empresa Inválida");
        }
        try {
            Empresa empresaFind = empresaRepo.findOne(empresa.getId());
            System.out.println("empresaFind.getLogo(): " + empresaFind.getLogo());
            HashMap<String, String> responseData = new HashMap<>();
            responseData.put("logo", empresaFind.getLogo());
            responseData.put("bodyLogo", empresaFind.getBodyLogo());
            return responseData;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
    
    @RequestMapping(value = "fileInsLogo", method = RequestMethod.POST)
    public boolean fileInsLogo(@RequestParam("file") MultipartFile file) throws ServletException {
        if (file == null){
            throw new ServletException("file Inválido");
        }
        try {
        	System.out.println("file: " + file.getOriginalFilename());
        	return true;
        }catch (Exception e){
        	System.out.println(e);
            throw new ServletException(e.getMessage());
        }
    }
}
