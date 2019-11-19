package com.irpf.business;

import com.irpf.repository.dao.ContribuinteDAODerby;
import com.irpf.repository.dao.ContribuinteDAOInterface;
import com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;
import java.util.List;

public class IrpfFacade {

    private ContribuinteDAOInterface contribuinteDAOInterface;
    private CalculoIrpfFactory calculoIrpfFactory;

    public IrpfFacade() {
        this.calculoIrpfFactory = CalculoIrpfFactory.getInstance();
        this.contribuinteDAOInterface = ContribuinteDAODerby.getInstance();
    }

    public void salvarContribuinte(ContribuinteDTO contribuinteDTO) {
        contribuinteDAOInterface.insert(contribuinteDTO);
    }

    public List<ContribuinteDTO> buscarContribuintes() {
        return contribuinteDAOInterface.findAll();
    }

    public List<ContribuinteDTO> buscarContribuinte(String nomeParcial) {
        return contribuinteDAOInterface.find(nomeParcial);
    }

    public BigDecimal calcularIrpf(ContribuinteDTO contribuinteDTO) {
        CalculoIrpfInterface calcula = calculoIrpfFactory.getCalculadora(contribuinteDTO);
        return calcula.calculaImposto(contribuinteDTO);
    }

}
