package com.irpf.repository.dto;

import java.math.BigDecimal;

public class ContribuinteDTO {
    private String nome;
    private Integer idade;
    private Integer dependentes;
    private BigDecimal contribuicaoOficial;
    private BigDecimal rendimentoTotal;
    private BigDecimal valorIRPF;

    public ContribuinteDTO() {
    }

    public ContribuinteDTO(String nome, Integer idade, Integer dependentes, BigDecimal contribuicaoOficial, BigDecimal rendimentoTotal, BigDecimal valorIRPF) {
        this.nome = nome;
        this.idade = idade;
        this.dependentes = dependentes;
        this.contribuicaoOficial = contribuicaoOficial;
        this.rendimentoTotal = rendimentoTotal;
        this.valorIRPF = valorIRPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getDependentes() {
        return dependentes;
    }

    public void setDependentes(Integer dependentes) {
        this.dependentes = dependentes;
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

    public BigDecimal getValorIRPF() {
        return valorIRPF;
    }

    public void setValorIRPF(BigDecimal valorIRPF) {
        this.valorIRPF = valorIRPF;
    }

    @Override
    public String toString() {
        return "ContribuinteDTO{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", dependentes=" + dependentes +
                ", contribuicaoOficial=" + contribuicaoOficial +
                ", rendimentoTotal=" + rendimentoTotal +
                ", valorIRPF=" + valorIRPF +
                '}';
    }
}
