package com.movilpyme.adenmaker.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Parametros {
	private long id;
	private int configuration;
	private String parametro;
	private String valor;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "parametros_seq_gen")
	@SequenceGenerator(name = "parametros_seq_gen", sequenceName = "PARAMETROS_SEQ")
	@Column(name = "ID", nullable = false, precision = 0)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "CONFIGURATION", nullable = false, precision = 0)
	public int getConfiguration() {
		return configuration;
	}

	public void setConfiguration(int configuration) {
		this.configuration = configuration;
	}

	@Basic
	@Column(name = "PARAMETRO", nullable = true, length = 20)
	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Basic
	@Column(name = "VALOR", nullable = true, length = 50)
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + configuration;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((parametro == null) ? 0 : parametro.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Parametros other = (Parametros) obj;
		if (configuration != other.configuration)
			return false;
		if (id != other.id)
			return false;
		if (parametro == null) {
			if (other.parametro != null)
				return false;
		} else if (!parametro.equals(other.parametro))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
