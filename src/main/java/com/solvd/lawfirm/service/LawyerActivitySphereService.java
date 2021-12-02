package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.LawyerActivitySphere;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface LawyerActivitySphereService {

    void create(LawyerActivitySphere lawyerActivitySphere) throws ParameterIsEmpty;

    List<LawyerActivitySphere> findAll() throws ResourceNotFoundException;

    LawyerActivitySphere findById(Long id) throws ResourceNotFoundException;

    int update(LawyerActivitySphere lawyerActivitySphere) throws ParameterIsEmpty;

    int delete(LawyerActivitySphere lawyerActivitySphere) throws ParameterIsEmpty;
}
