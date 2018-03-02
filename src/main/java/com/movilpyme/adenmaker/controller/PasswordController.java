package com.movilpyme.adenmaker.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movilpyme.adenmaker.domain.PasswordConfig;
import com.movilpyme.adenmaker.repository.ContraseniaPlantillaRepo;

@RestController
@RequestMapping("adm/contrasenia")
public class PasswordController {

	private final ContraseniaPlantillaRepo contraseniaPlantillaRepo;

	@Autowired
	public PasswordController(ContraseniaPlantillaRepo contraseniaPlantillaRepo) {
		super();
		this.contraseniaPlantillaRepo = contraseniaPlantillaRepo;
	}

	@RequestMapping(value = "byName/{name}", method = RequestMethod.POST)
	public List<PasswordConfig> getByName(@PathVariable String name) throws ServletException {
		if (name == null) {
			throw new ServletException("Configuraciòn Inválida");
		}
		List<PasswordConfig> list = contraseniaPlantillaRepo.findAllByName(name);
		if (list == null) {
			throw new ServletException("No se encontro correo");
		}
		return list;
	}
}