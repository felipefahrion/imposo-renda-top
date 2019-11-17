package com.irpf.presentation.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.math.BigDecimal;

public class CadastroModel implements Observable {

    private String nomeCompleto;
    private Integer idade;
    private Integer numeroDependentes;
    private BigDecimal contribuicaoOficial;
    private BigDecimal rendimentoTotal;
    private BigDecimal valorIrpf;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getNumeroDependentes() {
        return numeroDependentes;
    }

    public void setNumeroDependentes(Integer numeroDependentes) {
        this.numeroDependentes = numeroDependentes;
    }

    public BigDecimal getContribuicaoOficial() {
        return contribuicaoOficial;
    }

    public void setContribuicaoOficial(BigDecimal contribuicaoOficial) {
        this.contribuicaoOficial = contribuicaoOficial;
    }

    public BigDecimal getRendimentoTotal() {
        return rendimentoTotal;
    }

    public void setRendimentoTotal(BigDecimal rendimentoTotal) {
        this.rendimentoTotal = rendimentoTotal;
    }

    public BigDecimal getValorIrpf() {
        return valorIrpf;
    }

    public void setValorIrpf(BigDecimal valorIrpf) {
        this.valorIrpf = valorIrpf;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }

    @Override
    public String toString() {
        return "CadastroModel{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", idade=" + idade +
                ", numeroDependentes=" + numeroDependentes +
                ", contribuicaoOficial=" + contribuicaoOficial +
                ", rendimentoTotal=" + rendimentoTotal +
                ", valorIrpf=" + valorIrpf +
                '}';
    }
}
