package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.LawOffice;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface LawOfficeService {

    void create(LawOffice lawOffice) throws ParameterIsEmpty;

    List<LawOffice> findAll() throws ResourceNotFoundException;

    LawOffice findById(Long id) throws ResourceNotFoundException;

    int update(LawOffice lawOffice) throws ParameterIsEmpty;

    int delete(LawOffice lawOffice) throws ParameterIsEmpty;
}
