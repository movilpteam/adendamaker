package com.movilpyme.adenmaker.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movilpyme.adenmaker.domain.PasswordConfig;
import com.movilpyme.adenmaker.repository.PasswordConfigRepo;

@RestController
@RequestMapping("adm/contrasenia")
public class PasswordController {

	private final PasswordConfigRepo passwordConfigRepo;

	@Autowired
	public PasswordController(PasswordConfigRepo passwordConfigRepo) {
		super();
		this.passwordConfigRepo = passwordConfigRepo;
	}

	@RequestMapping(value = "byName/{name}", method = RequestMethod.POST)
	public List<PasswordConfig> getByName(@PathVariable String name) throws ServletException {
		if (name == null) {
			throw new ServletException("Configuraciòn Inválida");
		}
		List<PasswordConfig> list = passwordConfigRepo.findAllByName(name);
		if (list == null) {
			throw new ServletException("No se encontro correo");
		}
		return list;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public boolean save(@RequestBody PasswordConfig passwordConfig) throws ServletException {
        if (passwordConfig == null){
            throw new ServletException("Password Config Inválido");
        }
        try {
        	List<PasswordConfig> obj = passwordConfigRepo.findAllByName(passwordConfig.getName());
        	passwordConfig.setId(null != obj && obj.size() > 0 ? obj.get(0).getId() : 0);
        	passwordConfigRepo.save(passwordConfig);
            return true;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
}