package com.movilpyme.adenmaker.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codegen.relaxng.translate.Driver;
import com.movilpyme.adenmaker.bean.Args;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/adenmaker")
public class AdenmakerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdenmakerController.class);

	@RequestMapping(value = "/xml2xsd/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> getXml2xsd(@RequestBody Args args) {
		LOGGER.info("## --> AdenmakerController.getXml2xsd() ##");
		try {
			String resp = "" + new Driver().doMain(args.getArgs().split("\\,"));
			LOGGER.info(resp);
			LOGGER.info("## <-- AdenmakerController.getXml2xsd() ##");
			return new ResponseEntity<String>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Excepcion >> " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}