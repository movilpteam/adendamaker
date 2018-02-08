package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Addenda {
    private long idAddenda;
    private String nombre;
    private String descripcion;
    private String xsd;
    private Time fechaCreacion;
    private Time fechaEdicion;
    private long idEstatus;
    private long idEmpresa;
    private Empresa empresaByIdEmpresa;
    private Collection<AddendaEtiqueta> addendaEtiquetasByIdAddenda;
    private Collection<AddendaXmlns> addendaXmlnsByIdAddenda;
    private Collection<BitacoraAddenda> bitacoraAddendaByIdAddenda;
    private Collection<FacturasProcesadas> facturasProcesadasByIdAddenda;

    @Id
    @Column(name = "ID_ADDENDA", nullable = false, precision = 0)
    public long getIdAddenda() {
        return idAddenda;
    }

    public void setIdAddenda(long idAddenda) {
        this.idAddenda = idAddenda;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 100)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "XSD", nullable = true, length = 50)
    public String getXsd() {
        return xsd;
    }

    public void setXsd(String xsd) {
        this.xsd = xsd;
    }

    @Basic
    @Column(name = "FECHA_CREACION", nullable = false)
    public Time getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Time fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "FECHA_EDICION", nullable = false)
    public Time getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Time fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    @Basic
    @Column(name = "ID_ESTATUS", nullable = false, precision = 0)
    public long getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(long idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Basic
    @Column(name = "ID_EMPRESA", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addenda addenda = (Addenda) o;
        return idAddenda == addenda.idAddenda &&
                idEstatus == addenda.idEstatus &&
                idEmpresa == addenda.idEmpresa &&
                Objects.equals(nombre, addenda.nombre) &&
                Objects.equals(descripcion, addenda.descripcion) &&
                Objects.equals(xsd, addenda.xsd) &&
                Objects.equals(fechaCreacion, addenda.fechaCreacion) &&
                Objects.equals(fechaEdicion, addenda.fechaEdicion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAddenda, nombre, descripcion, xsd, fechaCreacion, fechaEdicion, idEstatus, idEmpresa);
    }

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
    public Empresa getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(Empresa empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }

    @OneToMany(mappedBy = "addendaByIdAddenda")
    public Collection<AddendaEtiqueta> getAddendaEtiquetasByIdAddenda() {
        return addendaEtiquetasByIdAddenda;
    }

    public void setAddendaEtiquetasByIdAddenda(Collection<AddendaEtiqueta> addendaEtiquetasByIdAddenda) {
        this.addendaEtiquetasByIdAddenda = addendaEtiquetasByIdAddenda;
    }

    @OneToMany(mappedBy = "addendaByIdAddenda")
    public Collection<AddendaXmlns> getAddendaXmlnsByIdAddenda() {
        return addendaXmlnsByIdAddenda;
    }

    public void setAddendaXmlnsByIdAddenda(Collection<AddendaXmlns> addendaXmlnsByIdAddenda) {
        this.addendaXmlnsByIdAddenda = addendaXmlnsByIdAddenda;
    }

    @OneToMany(mappedBy = "addendaByIdAddenda")
    public Collection<BitacoraAddenda> getBitacoraAddendaByIdAddenda() {
        return bitacoraAddendaByIdAddenda;
    }

    public void setBitacoraAddendaByIdAddenda(Collection<BitacoraAddenda> bitacoraAddendaByIdAddenda) {
        this.bitacoraAddendaByIdAddenda = bitacoraAddendaByIdAddenda;
    }

    @OneToMany(mappedBy = "addendaByIdAdenda")
    public Collection<FacturasProcesadas> getFacturasProcesadasByIdAddenda() {
        return facturasProcesadasByIdAddenda;
    }

    public void setFacturasProcesadasByIdAddenda(Collection<FacturasProcesadas> facturasProcesadasByIdAddenda) {
        this.facturasProcesadasByIdAddenda = facturasProcesadasByIdAddenda;
    }
}
