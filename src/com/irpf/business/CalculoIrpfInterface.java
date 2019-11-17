package com.irpf.business;

import com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;

public interface CalculoIrpfInterface {
    BigDecimal calculaImposto(ContribuinteDTO contribuinte);
}
