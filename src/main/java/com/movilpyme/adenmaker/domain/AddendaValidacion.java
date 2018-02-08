package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ADDENDA_VALIDACION", schema = "AW_ADMIN", catalog = "")
public class AddendaValidacion {
    private long idAddendaValidacion;
    private String regex;
    private long idEstatus;
    private Collection<AddendaCampo> addendaCamposByIdAddendaValidacion;

    @Id
    @Column(name = "ID_ADDENDA_VALIDACION", nullable = false, precision = 0)
    public long getIdAddendaValidacion() {
        return idAddendaValidacion;
    }

    public void setIdAddendaValidacion(long idAddendaValidacion) {
        this.idAddendaValidacion = idAddendaValidacion;
    }

    @Basic
    @Column(name = "REGEX", nullable = false, length = 500)
    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
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
        AddendaValidacion that = (AddendaValidacion) o;
        return idAddendaValidacion == that.idAddendaValidacion &&
                idEstatus == that.idEstatus &&
                Objects.equals(regex, that.regex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAddendaValidacion, regex, idEstatus);
    }

    @OneToMany(mappedBy = "addendaValidacionByIdAddendaValidacion")
    public Collection<AddendaCampo> getAddendaCamposByIdAddendaValidacion() {
        return addendaCamposByIdAddendaValidacion;
    }

    public void setAddendaCamposByIdAddendaValidacion(Collection<AddendaCampo> addendaCamposByIdAddendaValidacion) {
        this.addendaCamposByIdAddendaValidacion = addendaCamposByIdAddendaValidacion;
    }
}
