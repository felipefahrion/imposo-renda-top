package src.com.irpf.business;

import src.com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;

public interface CalculoIrpfInterface {
    BigDecimal calculaImposto(ContribuinteDTO contribuinte);
}
