package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Paperwork;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface PaperworkService {

    void create(Paperwork paperwork, Long paperworkTypeId, Long folderId) throws ParameterIsEmpty, ResourceNotFoundException;

    void create(Paperwork paperwork, Long paperworkTypeId, Long folderId, Long courtId, Long judgeId) throws ParameterIsEmpty, ResourceNotFoundException;

    List<Paperwork> findAll() throws ResourceNotFoundException;

    Paperwork findById(Long id) throws ResourceNotFoundException;

    int update(Paperwork paperwork) throws ParameterIsEmpty, ResourceNotFoundException;

    int delete(Paperwork paperwork) throws ParameterIsEmpty;
}
