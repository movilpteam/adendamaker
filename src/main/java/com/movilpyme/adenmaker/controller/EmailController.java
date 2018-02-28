package com.movilpyme.adenmaker.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.domain.Correo;
import com.movilpyme.adenmaker.domain.CorreoPlantilla;
import com.movilpyme.adenmaker.repository.CorreoPlantillaRepo;
import com.movilpyme.adenmaker.repository.EmailRepo;
import com.movilpyme.adenmaker.utils.Utils;

@RestController
@RequestMapping("adm/correo")
public class EmailController {

    private final EmailRepo emailRepo;
    private final CorreoPlantillaRepo correoPlantillaRepo;
	
	@Autowired
    public EmailController(EmailRepo emailRepo, CorreoPlantillaRepo correoPlantillaRepo) {
        this.emailRepo = emailRepo;
        this.correoPlantillaRepo = correoPlantillaRepo;
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
    
    @RequestMapping(value = "saveEmailTemplate", method = RequestMethod.POST)
    public boolean saveEmailTemplate(@RequestBody CorreoPlantilla correoPlantilla) throws ServletException {
        if (correoPlantilla == null){
            throw new ServletException("Correo Plantilla Inválido");
        }
        try {
            Correo correo = emailRepo.findOne(correoPlantilla.getIdCorreo());
            correoPlantilla.setCorreoByIdCorreo(correo);
            correoPlantillaRepo.save(correoPlantilla);
            return true;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "getPlantillas/{idcorreo}", method = RequestMethod.POST)
    public List<CorreoPlantilla> getPlantillasAllByCorreo(@PathVariable Long idcorreo) throws ServletException {
	    if (idcorreo == 0){
	        throw new ServletException("Configuracion Correo Inválida");
        }
        try {
	        Correo correo = emailRepo.findOne(idcorreo);
            List<CorreoPlantilla> plantillaList = correoPlantillaRepo.findAllByCorreoByIdCorreo(correo);
            return plantillaList;
        }catch (Exception e){
	        throw new ServletException(e.getMessage());
        }
    }
    
    @RequestMapping(value = "fileInsTemplate", method = RequestMethod.POST)
    public boolean fileInsTemplate(@RequestParam("file") MultipartFile file) throws ServletException {
        if (file == null){
            throw new ServletException("file Inválido");
        }
        try {
        	System.out.println("file: " + file);
        	return new Utils().copyFile(file, "/static/images/email/");
        }catch (Exception e){
        	System.out.println(e);
            throw new ServletException(e.getMessage());
        }
    }
}
