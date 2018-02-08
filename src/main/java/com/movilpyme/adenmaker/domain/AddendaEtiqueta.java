package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ADDENDA_ETIQUETA", schema = "AW_ADMIN", catalog = "")
public class AddendaEtiqueta {
    private long idAddendaEtiqueta;
    private Long idAddendaEtiquetaRelacion;
    private long idAddenda;
    private String nameSpace;
    private String etiqueta;
    private String descripcion;
    private Long minOccurs;
    private Long maxOccurs;
    private String isRequerido;
    private long idEstatus;
    private Collection<AddendaCampo> addendaCamposByIdAddendaEtiqueta;
    private Addenda addendaByIdAddenda;

    @Id
    @Column(name = "ID_ADDENDA_ETIQUETA", nullable = false, precision = 0)
    public long getIdAddendaEtiqueta() {
        return idAddendaEtiqueta;
    }

    public void setIdAddendaEtiqueta(long idAddendaEtiqueta) {
        this.idAddendaEtiqueta = idAddendaEtiqueta;
    }

    @Basic
    @Column(name = "ID_ADDENDA_ETIQUETA_RELACION", nullable = true, precision = 0)
    public Long getIdAddendaEtiquetaRelacion() {
        return idAddendaEtiquetaRelacion;
    }

    public void setIdAddendaEtiquetaRelacion(Long idAddendaEtiquetaRelacion) {
        this.idAddendaEtiquetaRelacion = idAddendaEtiquetaRelacion;
    }

    @Basic
    @Column(name = "ID_ADDENDA", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdAddenda() {
        return idAddenda;
    }

    public void setIdAddenda(long idAddenda) {
        this.idAddenda = idAddenda;
    }

    @Basic
    @Column(name = "NAME_SPACE", nullable = true, length = 100)
    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Basic
    @Column(name = "ETIQUETA", nullable = false, length = 100)
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 250)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "MIN_OCCURS", nullable = true, precision = 0)
    public Long getMinOccurs() {
        return minOccurs;
    }

    public void setMinOccurs(Long minOccurs) {
        this.minOccurs = minOccurs;
    }

    @Basic
    @Column(name = "MAX_OCCURS", nullable = true, precision = 0)
    public Long getMaxOccurs() {
        return maxOccurs;
    }

    public void setMaxOccurs(Long maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    @Basic
    @Column(name = "IS_REQUERIDO", nullable = false, length = 1)
    public String getIsRequerido() {
        return isRequerido;
    }

    public void setIsRequerido(String isRequerido) {
        this.isRequerido = isRequerido;
    }

    @Basic
    @Column(name = "ID_ESTATUS", nullable = false, precision = 0)
    public long getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(long idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddendaEtiqueta that = (AddendaEtiqueta) o;
        return idAddendaEtiqueta == that.idAddendaEtiqueta &&
                idAddenda == that.idAddenda &&
                idEstatus == that.idEstatus &&
                Objects.equals(idAddendaEtiquetaRelacion, that.idAddendaEtiquetaRelacion) &&
                Objects.equals(nameSpace, that.nameSpace) &&
                Objects.equals(etiqueta, that.etiqueta) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(minOccurs, that.minOccurs) &&
                Objects.equals(maxOccurs, that.maxOccurs) &&
                Objects.equals(isRequerido, that.isRequerido);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAddendaEtiqueta, idAddendaEtiquetaRelacion, idAddenda, nameSpace, etiqueta, descripcion, minOccurs, maxOccurs, isRequerido, idEstatus);
    }

    @OneToMany(mappedBy = "addendaEtiquetaByIdAddendaEtiqueta")
    public Collection<AddendaCampo> getAddendaCamposByIdAddendaEtiqueta() {
        return addendaCamposByIdAddendaEtiqueta;
    }

    public void setAddendaCamposByIdAddendaEtiqueta(Collection<AddendaCampo> addendaCamposByIdAddendaEtiqueta) {
        this.addendaCamposByIdAddendaEtiqueta = addendaCamposByIdAddendaEtiqueta;
    }

    @ManyToOne
    @JoinColumn(name = "ID_ADDENDA", referencedColumnName = "ID_ADDENDA", nullable = false)
    public Addenda getAddendaByIdAddenda() {
        return addendaByIdAddenda;
    }

    public void setAddendaByIdAddenda(Addenda addendaByIdAddenda) {
        this.addendaByIdAddenda = addendaByIdAddenda;
    }
}
