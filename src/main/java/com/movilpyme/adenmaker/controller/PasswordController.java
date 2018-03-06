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
import com.movilpyme.adenmaker.domain.PreguntasPwd;
import com.movilpyme.adenmaker.repository.PreguntasRepo;

@RestController
@RequestMapping("adm/contrasenia")
public class PasswordController {

	private final PasswordConfigRepo passwordConfigRepo;
	private final PreguntasRepo preguntasRepo;

	@Autowired
	public PasswordController(PasswordConfigRepo passwordConfigRepo, PreguntasRepo preguntasRepo) {
		super();
		this.passwordConfigRepo = passwordConfigRepo;
		this.preguntasRepo = preguntasRepo;
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

	@RequestMapping(value = "listQuestions", method = RequestMethod.POST)
    public List<PreguntasPwd> getQuestionsList() throws ServletException {
        List<PreguntasPwd> preguntas = (List<PreguntasPwd>) preguntasRepo.findAll();
        return preguntas;
    }

	@RequestMapping(value = "saveQuestions", method = RequestMethod.POST)
    public boolean saveQuestions(@RequestBody PreguntasPwd preguntasPwd) throws ServletException {
        if (preguntasPwd == null){
            throw new ServletException("Pregunta Inválida");
        }
        try {
        	preguntasRepo.save(preguntasPwd);
            return true;
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }

	@RequestMapping(value = "disabledQuestion/{id}", method = RequestMethod.POST)
    public Long disabledQuestion(@PathVariable Long id) throws ServletException {
        if (id == 0){
            throw new ServletException("Pregunta Inválida");
        }
        try {
        	preguntasRepo.delete(id);
            return new Long(1);
        }catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
}