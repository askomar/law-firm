package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.ServiceType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface ServiceTypeService {

    void create(ServiceType serviceType) throws ParameterIsEmpty;

    List<ServiceType> findAll() throws ResourceNotFoundException;

    ServiceType findById(Long id) throws ResourceNotFoundException;

    int update(ServiceType serviceType) throws ParameterIsEmpty;

    int delete(ServiceType serviceType) throws ParameterIsEmpty;
}
