package com.movilpyme.adenmaker.persistence.stp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import oracle.jdbc.OracleTypes;

public class PkgSpInsTemplate extends StoredProcedure {

	public PkgSpInsTemplate(JdbcTemplate template) {
		super(template, "PKG_APPLICATION_BURO.STP_INS_TEMPLATE");
		setFunction(false);
		
		declareParameter(new SqlParameter("P_ID", OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("P_NOMBRE", OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("P_ASUNTO", OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("P_BODY", OracleTypes.VARCHAR));

		declareParameter(new SqlOutParameter("P_SP_STATUS", OracleTypes.NUMBER));
		declareParameter(new SqlOutParameter("P_SP_STATUS_MSG", OracleTypes.VARCHAR));
		
		compile();
	}

	public Map<String, Object> execute(String id, String name, String asunto, String body) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("P_ID", id);
		input.put("P_NOMBRE", name);
		input.put("P_ASUNTO", asunto);
		input.put("P_BODY", body);
		return super.execute(input);
	}
}
