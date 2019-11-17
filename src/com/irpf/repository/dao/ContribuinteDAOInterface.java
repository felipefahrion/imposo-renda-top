package com.irpf.repository.dao;

import com.irpf.repository.dto.ContribuinteDTO;

import java.util.List;

public interface ContribuinteDAOInterface {

    void insert(ContribuinteDTO contribuinteDTO);
    ContribuinteDTO find(String cpf);
    void insert(List<ContribuinteDTO> people);
    List<ContribuinteDTO> findAll();
}
