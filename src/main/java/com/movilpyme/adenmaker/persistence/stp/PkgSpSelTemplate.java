package com.movilpyme.adenmaker.persistence.stp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.movilpyme.adenmaker.persistence.mapper.TemplateMapper;

import oracle.jdbc.OracleTypes;

public class PkgSpSelTemplate extends StoredProcedure {

	public PkgSpSelTemplate(JdbcTemplate template) {
		super(template, "PKG_APPLICATION_BURO.STP_SEL_TEMPLATE");
		setFunction(false);

		declareParameter(new SqlParameter("P_ID", OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("P_NOMBRE", OracleTypes.VARCHAR));

		declareParameter(new SqlOutParameter("P_SP_STATUS", OracleTypes.NUMBER));
		declareParameter(new SqlOutParameter("P_SP_STATUS_MSG", OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("P_CONTENT", OracleTypes.CURSOR, new TemplateMapper()));

		compile();
	}

	public Map<String, Object> execute(String id, String name) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("P_ID", id);
		input.put("P_NOMBRE", name);
		return super.execute(input);
	}
}
