package com.irpf.business;

import com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;

public class CalculoIrpfCompletoImp implements CalculoIrpfInterface {

    @Override
    public BigDecimal calculaImposto(ContribuinteDTO contribuinte) {
        Integer nDependentes = contribuinte.getDependentes();
        BigDecimal totalRendimentos = contribuinte.getRendimentoTotal();

        if (contribuinte.getIdade() < 65) {
            if (nDependentes <= 2) return BigDecimal.valueOf(1.02);
            else if (nDependentes <= 3 || nDependentes >= 5) {
                return totalRendimentos.multiply(BigDecimal.valueOf(1.035));
            } else return BigDecimal.valueOf(1.05);
        } else {
            if (nDependentes <= 2) return totalRendimentos.multiply(BigDecimal.valueOf(1.03));
            else if (nDependentes <= 3 || nDependentes >= 5)
                return totalRendimentos.multiply(BigDecimal.valueOf(1.045));
            else return totalRendimentos.multiply(BigDecimal.valueOf(1.06));
        }
    }
}
