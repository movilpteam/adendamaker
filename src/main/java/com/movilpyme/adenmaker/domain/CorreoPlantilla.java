package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CORREO_PLANTILLA", schema = "AW_ADMIN", catalog = "")
public class CorreoPlantilla {
    private long id;
    private String nombre;
    private String asunto;
    private String body;
    private String html;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
