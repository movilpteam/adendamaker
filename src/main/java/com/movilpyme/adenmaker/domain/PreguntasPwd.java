package com.movilpyme.adenmaker.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PREGUNTASPWD", schema = "AW_ADMIN", catalog = "")
public class PreguntasPwd {
	private long id;
	private String pregunta;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "preguntaspwd_seq_gen")
	@SequenceGenerator(name = "preguntaspwd_seq_gen", sequenceName = "PREGUNTASPWD_SEQ")
	@Column(name = "ID", nullable = false, precision = 0)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "PREGUNTA", nullable = false, length = 255)
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((pregunta == null) ? 0 : pregunta.hashCode());
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
		PreguntasPwd other = (PreguntasPwd) obj;
		if (id != other.id)
			return false;
		if (pregunta == null) {
			if (other.pregunta != null)
				return false;
		} else if (!pregunta.equals(other.pregunta))
			return false;
		return true;
	}
}
