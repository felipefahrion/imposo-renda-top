package com.irpf.repository.dao;

import com.irpf.repository.dto.ContribuinteDTO;

import java.util.List;

public interface ContribuinteDAOInterface {

    void insert(ContribuinteDTO contribuinteDTO);

    List<ContribuinteDTO> find(String nomeParcial);

    void insert(List<ContribuinteDTO> people);

    List<ContribuinteDTO> findAll();
}
