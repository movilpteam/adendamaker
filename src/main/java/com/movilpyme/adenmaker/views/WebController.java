package com.movilpyme.adenmaker.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.utils.Utils;

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
    
    @RequestMapping("/conf/logo")
    public String getLogo(Model model) {
        return "/pages/conf/logo.html";
    }
    
    @RequestMapping("/conf/upload")
    public String getLogoUpload(@RequestParam("file") MultipartFile file, Model model) {
    	new Utils().copyFile(file);
        return "/pages/conf/logo.html";
    }
    
    @RequestMapping("/conf/email")
    public String getCorreo(Model model) {
        return "/pages/conf/email.html";
    }
}
