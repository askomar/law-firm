package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.PaperWorkType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface PaperworkTypeService {

    void create(PaperWorkType paperWorkType) throws ParameterIsEmpty;

    List<PaperWorkType> findAll() throws ResourceNotFoundException;

    PaperWorkType findById(Long id) throws ResourceNotFoundException;

    int update(PaperWorkType paperWorkType) throws ParameterIsEmpty;

    int delete(PaperWorkType paperWorkType) throws ParameterIsEmpty;
}
