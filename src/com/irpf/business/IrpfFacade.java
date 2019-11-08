package src.com.irpf.business;

import src.com.irpf.repository.dao.ContribuinteDAODerby;
import src.com.irpf.repository.dao.ContribuinteDAOInterface;
import src.com.irpf.repository.dto.ContribuinteDTO;

import java.util.List;

public class IrpfFacade {

    private ContribuinteDAOInterface contribuinteDAOInterface;

    public IrpfFacade() {
        this.contribuinteDAOInterface = ContribuinteDAODerby.getInstance();
    }

    public void salvarContribuinte(ContribuinteDTO contribuinteDTO){
        contribuinteDAOInterface.insert(contribuinteDTO);
    }

    public List<ContribuinteDTO> buscarContribuintes(){
        return contribuinteDAOInterface.findAll();
    }

    public ContribuinteDTO buscarContribuinte(String cpf){
        return contribuinteDAOInterface.find(cpf);
    }


}
