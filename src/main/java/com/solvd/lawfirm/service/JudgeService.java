package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Judge;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface JudgeService {

    void create(Judge judge) throws ParameterIsEmpty;

    List<Judge> findAll() throws ResourceNotFoundException;

    Judge findById(Long id) throws ResourceNotFoundException;

    int update(Judge judge) throws ParameterIsEmpty;

    int delete(Judge judge) throws ParameterIsEmpty;
}
