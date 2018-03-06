package com.movilpyme.adenmaker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Empresa {
    private long id;
    private String nombre;
    private String logo;
    private String bodyLogo;
    private String direccion;
    private String telefono;
    private String responsable;
    private String correoContacto;
    private boolean enabled;
    @JsonIgnore
    private Collection<Addenda> addendaById;
    @JsonIgnore
    private Collection<FacturasProcesadas> facturasProcesadasById;
    @JsonIgnore
    private Collection<Usuarios> usuariosById;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="empresa_seq_gen")
    @SequenceGenerator(name="empresa_seq_gen", sequenceName="EMPRESA_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "LOGO", nullable = true, length = 100)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    @Basic
    @Column(name = "BODY_LOGO", columnDefinition = "CLOB")
    @Lob
    public String getBodyLogo() {
		return bodyLogo;
	}

	public void setBodyLogo(String bodyLogo) {
		this.bodyLogo = bodyLogo;
	}

	@Basic
    @Column(name = "DIRECCION", nullable = true, length = 200)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "TELEFONO", nullable = true, length = 20)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "RESPONSABLE", nullable = true, length = 100)
    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Basic
    @Column(name = "CORREO_CONTACTO", nullable = true, length = 100)
    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    @Basic
    @Type(type = "yes_no")
    @Column(name = "ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return id == empresa.id &&
                Objects.equals(nombre, empresa.nombre) &&
                Objects.equals(logo, empresa.logo) &&
                Objects.equals(direccion, empresa.direccion) &&
                Objects.equals(telefono, empresa.telefono) &&
                Objects.equals(responsable, empresa.responsable) &&
                Objects.equals(correoContacto, empresa.correoContacto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, logo, direccion, telefono, responsable, correoContacto);
    }

    @OneToMany(mappedBy = "empresaByIdEmpresa")
    public Collection<Addenda> getAddendaById() {
        return addendaById;
    }

    public void setAddendaById(Collection<Addenda> addendaById) {
        this.addendaById = addendaById;
    }

    @OneToMany(mappedBy = "empresaByIdEmpresa")
    public Collection<FacturasProcesadas> getFacturasProcesadasById() {
        return facturasProcesadasById;
    }

    public void setFacturasProcesadasById(Collection<FacturasProcesadas> facturasProcesadasById) {
        this.facturasProcesadasById = facturasProcesadasById;
    }

    @OneToMany(mappedBy = "empresaByIdEmpresa")
    public Collection<Usuarios> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(Collection<Usuarios> usuariosById) {
        this.usuariosById = usuariosById;
    }
}
