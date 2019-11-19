package com.irpf.presentation.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.math.BigDecimal;

public class CadastroModel implements Observable {

    private BigDecimal valorIrpf;

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
        return "CadastroModel{ valorIrpf=" + valorIrpf +
                '}';
    }
}
