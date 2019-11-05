package src.com.irpf.business;

import src.com.irpf.repository.dto.ContribuinteDTO;

public class CalculoIrpfFactory {
    public CalculoIrpfInterface calculaImposto(ContribuinteDTO contribuinteDTO) {
        if (contribuinteDTO.getDependentes() == null || contribuinteDTO.getContribuicaoOficial() == null){
            return new CalculoIrpfSimplificadoImp();
        } else {
            return new CalculoIrpfCompletoImp();
        }
    }
}
