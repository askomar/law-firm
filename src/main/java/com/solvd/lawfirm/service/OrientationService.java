package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Orientation;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface OrientationService {

    void create(Orientation orientation, Long lawyerId, Long lawyerActivitySphereId) throws ParameterIsEmpty, ResourceNotFoundException;

    List<Orientation> findAll() throws ResourceNotFoundException;

    Orientation findById(Long id) throws ResourceNotFoundException;

    int update(Orientation orientation) throws ParameterIsEmpty, ResourceNotFoundException;

    int delete(Orientation orientation) throws ParameterIsEmpty;
}
