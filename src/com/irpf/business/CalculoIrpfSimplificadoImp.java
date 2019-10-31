package src.com.irpf.business;

import src.com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;

public class CalculoIrpfSimplificadoImp implements CalculoIrpfInterface {

    @Override
    public BigDecimal calculaImposto(ContribuinteDTO contribuinteDTO) {
        BigDecimal baseCalculo = contribuinteDTO.getRendimentoTotal().subtract(contribuinteDTO.getContribuicaoOficial());
        if (baseCalculo.compareTo(BigDecimal.ZERO) < 0) throw new RuntimeException();
        else if (baseCalculo.compareTo(BigDecimal.valueOf(12000)) <= 0) return baseCalculo;
        else if ((baseCalculo.compareTo(BigDecimal.valueOf(12000)) > 0) && (baseCalculo.compareTo(BigDecimal.valueOf(24000)) < 0)) return  baseCalculo.multiply(BigDecimal.valueOf(1.15));
        else return baseCalculo.multiply(BigDecimal.valueOf(1.27));
    }
}
