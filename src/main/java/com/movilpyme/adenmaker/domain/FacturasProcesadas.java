package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "FACTURAS_PROCESADAS", schema = "AW_ADMIN", catalog = "")
public class FacturasProcesadas {
    private long id;
    private Time fecha;
    private String rfcReceptor;
    private String uuid;
    private String sello;
    private String numCertificado;
    private String selloSat;
    private long idAdenda;
    private long idEmpresa;
    private Addenda addendaByIdAdenda;
    private Empresa empresaByIdEmpresa;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FECHA", nullable = true)
    public Time getFecha() {
        return fecha;
    }

    public void setFecha(Time fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "RFC_RECEPTOR", nullable = true, length = 14)
    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    @Basic
    @Column(name = "UUID", nullable = true, length = 100)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "SELLO", nullable = true, length = 200)
    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    @Basic
    @Column(name = "NUM_CERTIFICADO", nullable = true, length = 20)
    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    @Basic
    @Column(name = "SELLO_SAT", nullable = true, length = 200)
    public String getSelloSat() {
        return selloSat;
    }

    public void setSelloSat(String selloSat) {
        this.selloSat = selloSat;
    }

    @Basic
    @Column(name = "ID_ADENDA", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdAdenda() {
        return idAdenda;
    }

    public void setIdAdenda(long idAdenda) {
        this.idAdenda = idAdenda;
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
        FacturasProcesadas that = (FacturasProcesadas) o;
        return id == that.id &&
                idAdenda == that.idAdenda &&
                idEmpresa == that.idEmpresa &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(rfcReceptor, that.rfcReceptor) &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(sello, that.sello) &&
                Objects.equals(numCertificado, that.numCertificado) &&
                Objects.equals(selloSat, that.selloSat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fecha, rfcReceptor, uuid, sello, numCertificado, selloSat, idAdenda, idEmpresa);
    }

    @ManyToOne
    @JoinColumn(name = "ID_ADENDA", referencedColumnName = "ID_ADDENDA", nullable = false)
    public Addenda getAddendaByIdAdenda() {
        return addendaByIdAdenda;
    }

    public void setAddendaByIdAdenda(Addenda addendaByIdAdenda) {
        this.addendaByIdAdenda = addendaByIdAdenda;
    }

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
    public Empresa getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(Empresa empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }
}
