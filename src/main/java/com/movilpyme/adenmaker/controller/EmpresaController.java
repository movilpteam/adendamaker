package com.movilpyme.adenmaker.controller;

import com.movilpyme.adenmaker.domain.Empresa;
import com.movilpyme.adenmaker.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
        	empresaFind.setId(empresa.getId());
        	empresaFind.setLogo(empresa.getLogo());
        	empresaRepo.save(empresaFind);
            return true;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
}
