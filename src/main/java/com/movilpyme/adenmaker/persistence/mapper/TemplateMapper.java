package com.movilpyme.adenmaker.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.movilpyme.adenmaker.domain.CorreoPlantilla;

public class TemplateMapper implements ParameterizedRowMapper<CorreoPlantilla>{
	
	public CorreoPlantilla mapRow(ResultSet rs, int rowNum) throws SQLException {
		CorreoPlantilla list = new CorreoPlantilla();
		list.setAsunto(rs.getString("ASUNTO") == null ? "" : rs.getString("ASUNTO"));
		list.setBody(rs.getString("BODY") == null ? "" : rs.getString("BODY"));
		list.setNombre(rs.getString("NOMBRE") == null ? "" : rs.getString("NOMBRE"));
		return list;
	}	
}