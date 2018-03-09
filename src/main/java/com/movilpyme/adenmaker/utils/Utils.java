package com.movilpyme.adenmaker.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.movilpyme.adenmaker.domain.Parametros;
import com.movilpyme.adenmaker.domain.PasswordConfig;
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
	
	/**
	 * @param parametros
	 * @return
	 */
	public Map<String, String> convertMap(List<Parametros> parametros){
		Map<String, String> map = new HashMap<>();
		for (Iterator<Parametros> iterator = parametros.iterator(); iterator.hasNext();) {
			Parametros object = iterator.next();
			map.put(object.getParametro().trim(), object.getValor().trim());
		}
		return map;
	}
	
	public String runtime(String command) {
		String console = "";
		System.out.println("\nRun: " + command);
		Runtime runtime = Runtime.getRuntime();
		try {
			Process p1 = runtime.exec(command);
			InputStream is = p1.getInputStream();
			int i = 0;
			while ((i = is.read()) != -1) {
				System.out.print("" + (char) i);
				console += "" + (char) i;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			console = e.getMessage();
		}
		return console;
	}
	
	public boolean createFile(String rootFile, String data) {
		boolean create = false;
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(rootFile);
			pw = new PrintWriter(fichero);
			pw.println(data.trim());
			fichero.close();
			create = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return create;
	}
	
	/**
	 * @param file
	 * @param parametros
	 * @return
	 * @throws Exception
	 */
	public boolean startXsdProcess(MultipartFile file, List<Parametros> parametros) throws Exception {
		LOGGER.debug("## --> Utils.startXsdProcess() ##");

		// Sacar parametros
		Map<String, String> map = convertMap(parametros);
		System.out.println(map);
		byte[] bytes = file.getBytes();
		String path = map.get(Constantes.PATH_XSD);
		String fileSrc = (path + Constantes.BACK_SLASH + file.getOriginalFilename())
				.replaceAll(Constantes.BACK_SLASH_DOUBLE, Constantes.BACK_SLASH);
		new File(path).mkdirs();
		FileCopyUtils.copy(bytes, new File(fileSrc));
		LOGGER.debug("Archivo copiado con exito " + fileSrc);
		System.out.println("Archivo copiado con exito " + fileSrc);
		// Generar jaxb
		String command = map.get(Constantes.COMMAND_XJC).replace("{name-xsd}",
				Constantes.DOUBLE_QUOTE + fileSrc + Constantes.DOUBLE_QUOTE) + " -d " + Constantes.DOUBLE_QUOTE + path
				+ Constantes.DOUBLE_QUOTE;
		String runtime = runtime(command);
		if (runtime.toLowerCase().contains(Constantes.ERROR.toLowerCase())) {
			runtime = runtime.replaceAll(Constantes.OPEN_CORCHETE, " ").replaceAll(Constantes.CLOSE_CORCHETE, " ");
			throw new Exception(runtime.toLowerCase().split(Constantes.ERROR.toLowerCase())[1]);
		}
		// Generar archivo java Main
		createFile(map.get(Constantes.PATH_XSD) + Constantes.BACK_SLASH + map.get(Constantes.SRC)
				+ Constantes.BACK_SLASH + Constantes.JAVA_TEST, Constantes.JAVA_TEST_DATA);
		// Generar archivo MANIFEST.MF
		createFile(map.get(Constantes.PATH_XSD) + Constantes.BACK_SLASH + Constantes.MANIFEST,
				Constantes.MANIFEST_DATA);
		// Compilar .java
		command = ("javac " + map.get(Constantes.PATH_XSD) + Constantes.BACK_SLASH + map.get(Constantes.SRC)
				+ Constantes.BACK_SLASH + "*.java").replaceAll(Constantes.BACK_SLASH_DOUBLE, Constantes.BACK_SLASH);
		runtime = runtime(command);
		if (!runtime.equals("") || runtime.length() > 7) {
			throw new Exception(runtime);
		}
		// Generar JAR
		String[] nameJar = file.getOriginalFilename().split("\\.");
		Runtime.getRuntime().exec("cmd /c cd " + map.get(Constantes.PATH_XSD)); 
		command = 
				"cmd.exe /c cd " + map.get(Constantes.PATH_XSD) + "&&" + 
				map.get(Constantes.COMMAND_JAR).replace("{data}",
				Constantes.DOUBLE_QUOTE + map.get(Constantes.PATH_XSD) + Constantes.BACK_SLASH
						+ (nameJar.length > 1 ? (nameJar[nameJar.length - 2]) : "") + ".jar" + Constantes.DOUBLE_QUOTE
						+ " " + Constantes.DOUBLE_QUOTE + map.get(Constantes.PATH_XSD) + Constantes.BACK_SLASH
						+ Constantes.MANIFEST + Constantes.DOUBLE_QUOTE)
				+ " " + map.get(Constantes.SRC) + Constantes.BACK_SLASH
				+ "*.*".replaceAll(Constantes.BACK_SLASH_DOUBLE, Constantes.BACK_SLASH);
		runtime = runtime(command);
		
		LOGGER.debug("## <-- Utils.startXsdProcess() ##");
		return true;
	}
}