package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.CourtType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface CourtTypeService {

    void create(CourtType courtType) throws ParameterIsEmpty;

    List<CourtType> findAll() throws ResourceNotFoundException;

    CourtType findById(Long id) throws ResourceNotFoundException;

    int update(CourtType courtType) throws ParameterIsEmpty;

    int delete(CourtType courtType) throws ParameterIsEmpty;
}
