package com.movilpyme.adenmaker.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Correo {
    private long id;
    private String nameUser;
    private String pwd;
    private String initServer;
    private String endServer;
    private String entrancePort;
    private String exitPort;
    private String issuerName;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="correo_seq_gen")
    @SequenceGenerator(name="correo_seq_gen", sequenceName="CORREO_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "NAME_USER", nullable = true, length = 100)
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	@Basic
    @Column(name = "PASSWORD", nullable = true, length = 200)
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Basic
    @Column(name = "INIT_SERVER", nullable = true, length = 100)
	public String getInitServer() {
		return initServer;
	}
	public void setInitServer(String initServer) {
		this.initServer = initServer;
	}
	
	@Basic
    @Column(name = "END_SERVER", nullable = true, length = 100)
	public String getEndServer() {
		return endServer;
	}
	public void setEndServer(String endServer) {
		this.endServer = endServer;
	}
	
	@Basic
    @Column(name = "ENTRANCE_PORT", nullable = true, length = 100)
	public String getEntrancePort() {
		return entrancePort;
	}
	public void setEntrancePort(String entrancePort) {
		this.entrancePort = entrancePort;
	}
	
	@Basic
    @Column(name = "EXIT_PORT", nullable = true, length = 100)
	public String getExitPort() {
		return exitPort;
	}
	public void setExitPort(String exitPort) {
		this.exitPort = exitPort;
	}
	
	@Basic
    @Column(name = "ISSUER_NAME", nullable = true, length = 100)
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
}
