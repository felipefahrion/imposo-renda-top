package src.com.irpf.business;

import src.com.irpf.repository.dto.ContribuinteDTO;

public class CalculoIrpfFactory {

    private static CalculoIrpfFactory ref;

    public static CalculoIrpfFactory getInstance() {
        if (ref == null)
            ref = new CalculoIrpfFactory();
        return ref;
    }

    public CalculoIrpfInterface calcula(ContribuinteDTO contribuinteDTO) {
        if (contribuinteDTO.getDependentes() == null || contribuinteDTO.getContribuicaoOficial() == null){
            return new CalculoIrpfSimplificadoImp();
        } else {
            return new CalculoIrpfCompletoImp();
        }
    }
}
