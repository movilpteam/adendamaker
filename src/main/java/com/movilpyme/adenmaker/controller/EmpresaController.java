package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Empresa;
import com.movilpyme.adenmaker.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("adm/empresa")
public class EmpresaController {

    private final EmpresaRepo empresaRepo;

    @Autowired
    public EmpresaController(EmpresaRepo empresaRepo) {
        this.empresaRepo = empresaRepo;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<Empresa> getEmpresaList() throws ServletException {
        List<Empresa> empresaList = (List<Empresa>) empresaRepo.findAll();
        return empresaList;
    }
}
