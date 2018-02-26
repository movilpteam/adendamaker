package com.movilpyme.adenmaker.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import java.util.Objects;

@Entity
public class Parametros {
    private long id;
    private String parametro;
    private String valor;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="parametros_seq_gen")
    @SequenceGenerator(name="parametros_seq_gen", sequenceName="PARAMETROS_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PARAMETRO", nullable = true, length = 20)
    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    @Basic
    @Column(name = "VALOR", nullable = true, length = 50)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parametros that = (Parametros) o;
        return id == that.id &&
                Objects.equals(parametro, that.parametro) &&
                Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, parametro, valor);
    }
}
