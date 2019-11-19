//package com.irpf.presentation.controller;
//
//import com.irpf.business.IrpfFacade;
//import com.irpf.presentation.model.CadastroModel;
//import com.irpf.repository.dto.ContribuinteDTO;
//
//import java.math.BigDecimal;
//
//public class HomeController {
//
//    //INICIA TUDO
//    private IrpfFacade irpfFacade;
//
//    //PASSA AS VIEWS QUE OBSERVAM PARA A MODEL
//    public HomeController() {
//        this.irpfFacade = new IrpfFacade();
//    }
//
//    public BigDecimal calcularIrpf(CadastroModel cadastroModel) {
//        ContribuinteDTO contribuinteDTO = getContribuinteDTO(cadastroModel);
//        BigDecimal valorIrpf = irpfFacade.calcularIrpf(contribuinteDTO);
//        cadastroModel.setValorIrpf(valorIrpf);
//        return valorIrpf;
//    }
//
//}
