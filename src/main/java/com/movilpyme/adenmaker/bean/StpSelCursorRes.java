package com.movilpyme.adenmaker.bean;

import java.io.Serializable;
import java.util.List;

public class StpSelCursorRes implements Serializable {
	
	private static final long serialVersionUID = -7199921765210378474L;
	
	protected int pSpStatus;
	protected String pSpStatusMsg;
	protected List<Object> pContent;

	public int getpSpStatus() {
		return pSpStatus;
	}

	public void setpSpStatus(int pSpStatus) {
		this.pSpStatus = pSpStatus;
	}

	public String getpSpStatusMsg() {
		return pSpStatusMsg;
	}

	public void setpSpStatusMsg(String pSpStatusMsg) {
		this.pSpStatusMsg = pSpStatusMsg;
	}

	public List<Object> getpContent() {
		return pContent;
	}

	public void setpContent(List<Object> pContent) {
		this.pContent = pContent;
	}
}
