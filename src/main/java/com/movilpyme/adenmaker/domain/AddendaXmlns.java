package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ADDENDA_XMLNS", schema = "AW_ADMIN", catalog = "")
public class AddendaXmlns {
    private long idAddendaXmlns;
    private long idAddenda;
    private String suffix;
    private String xmlns;
    private Addenda addendaByIdAddenda;

    @Id
    @Column(name = "ID_ADDENDA_XMLNS", nullable = false, precision = 0)
    public long getIdAddendaXmlns() {
        return idAddendaXmlns;
    }

    public void setIdAddendaXmlns(long idAddendaXmlns) {
        this.idAddendaXmlns = idAddendaXmlns;
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
    @Column(name = "SUFFIX", nullable = true, length = 20)
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Basic
    @Column(name = "XMLNS", nullable = true, length = 250)
    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddendaXmlns that = (AddendaXmlns) o;
        return idAddendaXmlns == that.idAddendaXmlns &&
                idAddenda == that.idAddenda &&
                Objects.equals(suffix, that.suffix) &&
                Objects.equals(xmlns, that.xmlns);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAddendaXmlns, idAddenda, suffix, xmlns);
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
