package com.movilpyme.adenmaker.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

@Entity
public class Correo {
    private long id;
    private String nameUser;
    private String pwd;
    private String endServer;
    private String entrancePort;
    private boolean certificate;
    
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
    @Type(type = "yes_no")
    @Column(name = "CERTIFICATE")
	public boolean isCertificate() {
		return certificate;
	}
	public void setCertificate(boolean certificate) {
		this.certificate = certificate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (certificate ? 1231 : 1237);
		result = prime * result + ((endServer == null) ? 0 : endServer.hashCode());
		result = prime * result + ((entrancePort == null) ? 0 : entrancePort.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nameUser == null) ? 0 : nameUser.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Correo other = (Correo) obj;
		if (certificate != other.certificate)
			return false;
		if (endServer == null) {
			if (other.endServer != null)
				return false;
		} else if (!endServer.equals(other.endServer))
			return false;
		if (entrancePort == null) {
			if (other.entrancePort != null)
				return false;
		} else if (!entrancePort.equals(other.entrancePort))
			return false;
		if (id != other.id)
			return false;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}
}
