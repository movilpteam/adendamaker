package com.movilpyme.adenmaker.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movilpyme.adenmaker.domain.Correo;
import com.movilpyme.adenmaker.repository.EmailRepo;

@RestController
@RequestMapping("adm/correo")
public class EmailController {

    private final EmailRepo emailRepo;

    @Autowired
    public EmailController(EmailRepo emailRepo) {
        this.emailRepo = emailRepo;
    }

    @RequestMapping(value = "byId/{id}", method = RequestMethod.POST)
    public Correo getEmailById(@PathVariable Long id) throws ServletException {
        if (id == 0){
            throw new ServletException("ID Inválido");
        }
        Correo correo = emailRepo.findOne(id);
        if (correo == null){
            throw new ServletException("No se encontro correo");
        }
        return correo;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<Correo> getEmailList() throws ServletException {
        List<Correo> emailList = (List<Correo>) emailRepo.findAll();
        return emailList;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public boolean saveEmail(@RequestBody Correo correo) throws ServletException {
        if (correo == null){
            throw new ServletException("Correo Inválido");
        }
        try {
        	emailRepo.save(correo);
            return true;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
}
