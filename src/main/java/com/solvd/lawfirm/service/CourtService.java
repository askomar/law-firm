package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Court;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface CourtService {

    void create(Court court, Long courtTypeId) throws ParameterIsEmpty, ResourceNotFoundException;

    List<Court> findAll() throws ResourceNotFoundException;

    Court findById(Long id) throws ResourceNotFoundException;

    int update(Court court) throws ParameterIsEmpty, ResourceNotFoundException;

    int delete(Court court) throws ParameterIsEmpty;
}
