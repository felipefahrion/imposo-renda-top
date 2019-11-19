package com.irpf.business;

import com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;

public class CalculoIrpfCompletoImp implements CalculoIrpfInterface {

    @Override
    public BigDecimal calculaImposto(ContribuinteDTO contribuinte) {
        Integer nDependentes = contribuinte.getDependentes();
        BigDecimal totalRendimentos = contribuinte.getRendimentoTotal();

        BigDecimal baseCalculo = totalRendimentos.subtract(contribuinte.getContribuicaoOficial());
        BigDecimal valorDesconto = getValorDesconto(contribuinte.getIdade(), nDependentes, totalRendimentos);
        BigDecimal baseFinal = baseCalculo.subtract(baseCalculo.multiply(valorDesconto));
        if (baseFinal.compareTo(BigDecimal.valueOf(12000)) < 0) {
            return BigDecimal.ZERO;
        }
        if (baseFinal.compareTo(BigDecimal.valueOf(24000)) < 0) {
            return baseFinal.multiply(BigDecimal.valueOf(0.15));
        }
        return baseFinal.multiply(BigDecimal.valueOf(0.275));
    }

    private BigDecimal getValorDesconto(Integer idade, Integer nDependentes, BigDecimal totalRendimentos) {
        if (idade < 65) {
            if (nDependentes <= 2) return BigDecimal.valueOf(0.02);
            else if (nDependentes != 4) {
                return totalRendimentos.multiply(BigDecimal.valueOf(0.035));
            } else return BigDecimal.valueOf(0.05);
        } else {
            if (nDependentes <= 2) return totalRendimentos.multiply(BigDecimal.valueOf(0.03));
            else if (nDependentes != 4)
                return totalRendimentos.multiply(BigDecimal.valueOf(0.045));
            else return totalRendimentos.multiply(BigDecimal.valueOf(0.06));
        }
    }
}
