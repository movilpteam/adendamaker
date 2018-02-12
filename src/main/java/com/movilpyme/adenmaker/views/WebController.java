package com.movilpyme.adenmaker.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String getLogin(Model model){
        //model.addAttribute("page", "login");
        return "login.html";
    }

    @RequestMapping("/home")
    public String getHome(Model model){
        return "pages/home.html";
    }

    // Direcciones Modulo de Administracion //

    @RequestMapping("/adm/usuarios")
    public String getUsuariosList(Model model){
        return "/pages/adm/usuarios.html";
    }
}
