package com.movilpyme.adenmaker.persistence.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.movilpyme.adenmaker.bean.StpSelCursorRes;
import com.movilpyme.adenmaker.persistence.dao.ApplicationBuroDao;
import com.movilpyme.adenmaker.persistence.stp.PkgSpSelTemplate;

@Repository
public class ApplicationBuroDaoImpl implements ApplicationBuroDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBuroDaoImpl.class);

	@Autowired
	private JdbcTemplate corpoJdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public StpSelCursorRes stpSelTemplate(String id, String name) throws Exception {
		LOGGER.debug("## --> CatalogoDaoImpl.stpSelTemplate() ##");
		StpSelCursorRes stpSelCursorRes = null;
		PkgSpSelTemplate sp = new PkgSpSelTemplate(corpoJdbcTemplate);
		Map<String, Object> mapSp = sp.execute(id, name);
		if(!mapSp.isEmpty()){
			stpSelCursorRes = new StpSelCursorRes();
			stpSelCursorRes.setpSpStatus(Integer.parseInt("" + mapSp.get("P_SP_STATUS")));
			stpSelCursorRes.setpSpStatusMsg("" + mapSp.get("P_SP_STATUS_MSG"));
			List<Object> listCursor = (List<Object>) mapSp.get("P_CONTENT");
			stpSelCursorRes.setpContent(listCursor);
		}
		LOGGER.debug("## <-- CatalogoDaoImpl.stpSelTemplate() ##");
		return stpSelCursorRes;
	}
}