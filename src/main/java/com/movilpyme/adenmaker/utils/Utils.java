package com.movilpyme.adenmaker.utils;

import java.io.File;
import java.io.Serializable;

import com.movilpyme.adenmaker.domain.Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.views.WebController;

public class Utils implements Serializable{
	private static final long serialVersionUID = 5013185273182860568L;
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
	
	public boolean copyFile(MultipartFile file) {
		LOGGER.debug("## --> Utils.copyFile() ##");
		try{
    		byte[] bytes = file.getBytes();
    		String path =  WebController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    		new File(path + "/static/images/logos/").mkdirs();
    		FileCopyUtils.copy(bytes, new File(path + "/static/images/logos/" + file.getOriginalFilename()));
    		LOGGER.debug("Archivo copiado con exito " + path + "/static/images/logos/" + file.getOriginalFilename());
		}	catch (Exception e) {
    		LOGGER.debug("## Exception e: " + e + "##");
			return false;
		}
		LOGGER.debug("## <-- Utils.copyFile() ##");
		return true;
	}
}
