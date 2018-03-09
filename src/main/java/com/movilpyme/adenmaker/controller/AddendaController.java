package com.movilpyme.adenmaker.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.repository.ParametrosConfigRepo;
import com.movilpyme.adenmaker.utils.Constantes;
import com.movilpyme.adenmaker.utils.Utils;

@RestController
@RequestMapping("addenda/upload")
public class AddendaController {
	
	private final ParametrosConfigRepo parametrosConfigRepo;
    
	@Autowired
    public AddendaController(ParametrosConfigRepo parametrosConfigRepo) {
		super();
		this.parametrosConfigRepo = parametrosConfigRepo;
	}
	
	@RequestMapping(value = "xsd", method = RequestMethod.POST)
    public boolean fileInsAddenda(@RequestParam("file") MultipartFile file) throws ServletException {
        if (file == null){
            throw new ServletException("file Inválido");
        }
        try {
        	System.out.println("Procesando Archivo: " + file.getOriginalFilename());
        	new Utils().startXsdProcess(file, parametrosConfigRepo.findAllByName(Constantes.ADDENDA));
        	return true;
        }catch (Exception e){
        	System.out.println(e);
            throw new ServletException(e.getMessage());
        }
    }
}
