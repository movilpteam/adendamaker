package com.movilpyme.adenmaker.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movilpyme.adenmaker.domain.Empresa;
import com.movilpyme.adenmaker.repository.EmpresaRepo;

@RestController
@RequestMapping("adm/empresa")
public class EmpresaController {

    private final EmpresaRepo empresaRepo;

    @Autowired
    public EmpresaController(EmpresaRepo empresaRepo) {
        this.empresaRepo = empresaRepo;
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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<Empresa> getEmpresaList() throws ServletException {
        List<Empresa> empresaList = (List<Empresa>) empresaRepo.findAll();
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
    public boolean updateEmpresa(@RequestBody Empresa empresa) throws ServletException {
        if (empresa == null){
            throw new ServletException("Empresa Inválida");
        }
        try {
        	Empresa empresaFind = empresaRepo.findOne(empresa.getId());
        	empresaFind.setLogo(empresa.getLogo());
        	empresaRepo.save(empresaFind);
            return true;
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
            return responseData;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
}
