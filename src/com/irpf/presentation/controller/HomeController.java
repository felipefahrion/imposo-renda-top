package src.com.irpf.presentation.controller;

import src.com.irpf.business.IrpfFacade;
import src.com.irpf.presentation.model.CadastroModel;
import src.com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;

public class HomeController {

    //INICIA TUDO
    private IrpfFacade irpfFacade;

    //PASSA AS VIEWS QUE OBSERVAM PARA A MODEL
    public HomeController() {
        this.irpfFacade = new IrpfFacade();
    }

    public void calcularIrpf(CadastroModel cadastroModel) {
        ContribuinteDTO contribuinteDTO = getContribuinteDTO(cadastroModel);
        BigDecimal valorIrpf = irpfFacade.calcularIrpf(contribuinteDTO);
        cadastroModel.setValorIrpf(valorIrpf.toString());
    }

    private ContribuinteDTO getContribuinteDTO(CadastroModel cadastroModel) {
        ContribuinteDTO contribuinteDTO = new ContribuinteDTO();
        contribuinteDTO.setCPF(cadastroModel.getCPF());
        contribuinteDTO.setContribuicaoOficial(cadastroModel.getContribuicaoOficial());
        contribuinteDTO.setDependentes(cadastroModel.getDependentes());
        contribuinteDTO.setIdade(cadastroModel.getIdade());
        contribuinteDTO.setNome(cadastroModel.getNome());
        contribuinteDTO.setRendimentoTotal(cadastroModel.getRendimentoTotal());
        return contribuinteDTO;
    }
}
