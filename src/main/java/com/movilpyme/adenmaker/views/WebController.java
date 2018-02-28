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

    @RequestMapping("/adm/empresas")
    public String getEmpresaList(Model model) {
        return "/pages/adm/empresa.html";
    }

    // Direcciones Modulo de Configuracion //
    
    @RequestMapping("/conf/logo")
    public String getLogo(Model model) {
        return "/pages/conf/logo.html";
    }
    
    @RequestMapping("/conf/email")
    public String getCorreo(Model model) {
        return "/pages/conf/email.html";
    }
    
    @RequestMapping("/conf/password")
    public String getPassword(Model model) {
        return "/pages/conf/passwords.html";
    }

    // Direcciones Modulo Addendas //

    @RequestMapping("/addenda/list")
    public String getListaAddendas(Model model) {
        return "/pages/addenda/addenda_list.html";
    }

    @RequestMapping("/addenda/create_edit")
    public String getAddendasForm(Model model) {
        return "/pages/addenda/addenda.html";
    }

}
