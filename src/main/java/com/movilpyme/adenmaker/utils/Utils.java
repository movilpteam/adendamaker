package com.movilpyme.adenmaker.utils;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.movilpyme.adenmaker.domain.PasswordConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.views.WebController;

public class Utils implements Serializable {
	private static final long serialVersionUID = 5013185273182860568L;
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	/**
	 * @param file
	 * @param src
	 * @return
	 */
	public boolean copyFile(MultipartFile file, String src) {
		LOGGER.debug("## --> Utils.copyFile() ##");
		try {
			byte[] bytes = file.getBytes();
			String path = WebController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			new File(path + src).mkdirs();
			FileCopyUtils.copy(bytes, new File(path + src + file.getOriginalFilename()));
			LOGGER.debug("Archivo copiado con exito " + path + src + file.getOriginalFilename());
            System.out.println(path + src + file.getOriginalFilename());
		} catch (Exception e) {
			LOGGER.debug("## Exception e: " + e + "##");
			return false;
		}
		LOGGER.debug("## <-- Utils.copyFile() ##");
		return true;
	}

	public static String getPwdRegex(List<PasswordConfig> configList) {
        StringBuilder regex = new StringBuilder();
        regex.append("^(?=.*[a-z])");
        for (PasswordConfig config: configList) {
            switch (config.getName()){
                case Constantes.LENGTH:
                    regex.append("(?=.{").append(config.getValor()).append(",})");break;
                case Constantes.NUMBERCHAR:
                    if (config.getValor() != null) {
                        regex.append("(?=.*\\d)");
                    }
                    break;
                case Constantes.UPPERCASE:
                    if (config.getValor() != null) {
                        regex.append("(?=.*[A-Z])");
                    }
                    break;
                case Constantes.SPECIALCHAR:
                    if (config.getValor() != null) {
                        regex.append("(?=.*[@#$%&^()~*{}=])");
                    }
                    break;
            }
        }
        regex.append(".*$");
        return regex.toString();
	}
}
