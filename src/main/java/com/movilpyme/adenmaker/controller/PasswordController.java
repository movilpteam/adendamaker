package com.movilpyme.adenmaker.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movilpyme.adenmaker.domain.Parametros;
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

	@RequestMapping(value = "byId/{configuration}", method = RequestMethod.POST)
	public List<Parametros> getConfigurationById(@PathVariable Long configuration) throws ServletException {
		if (configuration == 0) {
			throw new ServletException("Configuraciòn Inválida");
		}
		List<Parametros> list = contraseniaPlantillaRepo.findAllByConfiguration(Integer.parseInt("" + configuration));
		if (list == null) {
			throw new ServletException("No se encontro correo");
		}
		return list;
	}
}