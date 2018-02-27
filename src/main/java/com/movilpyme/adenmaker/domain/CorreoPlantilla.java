package com.movilpyme.adenmaker.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CORREO_PLANTILLA", schema = "AW_ADMIN", catalog = "")
public class CorreoPlantilla {
    private Long id;
    private String nombre;
    private String asunto;
    private String body;
    private String html;
    private long idCorreo;
    private Correo correoByIdCorreo;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="correo_plantilla_seq_gen")
    @SequenceGenerator(name="correo_plantilla_seq_gen", sequenceName="CORREO_PLANTILLA_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ASUNTO", nullable = true, length = 30)
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Basic
    @Column(name = "BODY", nullable = true, length = 500)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "HTML", nullable = true, length = 1)
    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
    @Basic
    @Column(name = "ID_CORREO", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(long idCorreo) {
		this.idCorreo = idCorreo;
	}

	@ManyToOne
    @JoinColumn(name = "ID_CORREO", referencedColumnName = "ID", nullable = false)
    public Correo getCorreoByIdCorreo() {
		return correoByIdCorreo;
	}

	public void setCorreoByIdCorreo(Correo correoByIdCorreo) {
		this.correoByIdCorreo = correoByIdCorreo;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorreoPlantilla that = (CorreoPlantilla) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(asunto, that.asunto) &&
                Objects.equals(body, that.body) &&
                Objects.equals(html, that.html);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, asunto, body, html);
    }
}
