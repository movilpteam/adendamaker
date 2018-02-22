package com.movilpyme.adenmaker.persistence.dao;

import com.movilpyme.adenmaker.bean.StpSelCursorRes;

public interface ApplicationBuroDao {

	StpSelCursorRes stpSelTemplate(String id, String name) throws Exception;
	
	StpSelCursorRes stpInsTemplate(String id, String name, String asunto, String body) throws Exception;
}