package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface LawyerService {

    void create(Lawyer lawyer, Long officeId) throws ParameterIsEmpty, ResourceNotFoundException;

    List<Lawyer> findAll() throws ResourceNotFoundException;

    Lawyer findById(Long id) throws ResourceNotFoundException;

    int update(Lawyer lawyer) throws ParameterIsEmpty, ResourceNotFoundException;

    int delete(Lawyer lawyer) throws ParameterIsEmpty;
}
