package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ADDENDA_CAMPO", schema = "AW_ADMIN", catalog = "")
public class AddendaCampo {
    private long idAddendaCampo;
    private long idAddendaEtiqueta;
    private String etiqueta;
    private String valorDefecto;
    private long idAddendaValidacion;
    private long isRequerido;
    private Long limiteInferior;
    private Long limiteSuperior;
    private long idEstatus;
    private AddendaEtiqueta addendaEtiquetaByIdAddendaEtiqueta;
    private AddendaValidacion addendaValidacionByIdAddendaValidacion;

    @Id
    @Column(name = "ID_ADDENDA_CAMPO", nullable = false, precision = 0)
    public long getIdAddendaCampo() {
        return idAddendaCampo;
    }

    public void setIdAddendaCampo(long idAddendaCampo) {
        this.idAddendaCampo = idAddendaCampo;
    }

    @Basic
    @Column(name = "ID_ADDENDA_ETIQUETA", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdAddendaEtiqueta() {
        return idAddendaEtiqueta;
    }

    public void setIdAddendaEtiqueta(long idAddendaEtiqueta) {
        this.idAddendaEtiqueta = idAddendaEtiqueta;
    }

    @Basic
    @Column(name = "ETIQUETA", nullable = false, length = 150)
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Basic
    @Column(name = "VALOR_DEFECTO", nullable = true, length = 50)
    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    @Basic
    @Column(name = "ID_ADDENDA_VALIDACION", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdAddendaValidacion() {
        return idAddendaValidacion;
    }

    public void setIdAddendaValidacion(long idAddendaValidacion) {
        this.idAddendaValidacion = idAddendaValidacion;
    }

    @Basic
    @Column(name = "IS_REQUERIDO", nullable = false, precision = 0)
    public long getIsRequerido() {
        return isRequerido;
    }

    public void setIsRequerido(long isRequerido) {
        this.isRequerido = isRequerido;
    }

    @Basic
    @Column(name = "LIMITE_INFERIOR", nullable = true, precision = 0)
    public Long getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Long limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    @Basic
    @Column(name = "LIMITE_SUPERIOR", nullable = true, precision = 0)
    public Long getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Long limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
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
        AddendaCampo that = (AddendaCampo) o;
        return idAddendaCampo == that.idAddendaCampo &&
                idAddendaEtiqueta == that.idAddendaEtiqueta &&
                idAddendaValidacion == that.idAddendaValidacion &&
                isRequerido == that.isRequerido &&
                idEstatus == that.idEstatus &&
                Objects.equals(etiqueta, that.etiqueta) &&
                Objects.equals(valorDefecto, that.valorDefecto) &&
                Objects.equals(limiteInferior, that.limiteInferior) &&
                Objects.equals(limiteSuperior, that.limiteSuperior);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAddendaCampo, idAddendaEtiqueta, etiqueta, valorDefecto, idAddendaValidacion, isRequerido, limiteInferior, limiteSuperior, idEstatus);
    }

    @ManyToOne
    @JoinColumn(name = "ID_ADDENDA_ETIQUETA", referencedColumnName = "ID_ADDENDA_ETIQUETA", nullable = false)
    public AddendaEtiqueta getAddendaEtiquetaByIdAddendaEtiqueta() {
        return addendaEtiquetaByIdAddendaEtiqueta;
    }

    public void setAddendaEtiquetaByIdAddendaEtiqueta(AddendaEtiqueta addendaEtiquetaByIdAddendaEtiqueta) {
        this.addendaEtiquetaByIdAddendaEtiqueta = addendaEtiquetaByIdAddendaEtiqueta;
    }

    @ManyToOne
    @JoinColumn(name = "ID_ADDENDA_VALIDACION", referencedColumnName = "ID_ADDENDA_VALIDACION", nullable = false)
    public AddendaValidacion getAddendaValidacionByIdAddendaValidacion() {
        return addendaValidacionByIdAddendaValidacion;
    }

    public void setAddendaValidacionByIdAddendaValidacion(AddendaValidacion addendaValidacionByIdAddendaValidacion) {
        this.addendaValidacionByIdAddendaValidacion = addendaValidacionByIdAddendaValidacion;
    }
}
