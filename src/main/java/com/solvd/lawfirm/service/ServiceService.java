package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Service;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface ServiceService {

    void create(Service service, Long serviceTypeId, Long lawyerId) throws ParameterIsEmpty, ResourceNotFoundException;

    void create(Service service, Long serviceTypeId, Long lawyerId, Long paperworkId) throws ParameterIsEmpty, ResourceNotFoundException;

    List<Service> findAll() throws ResourceNotFoundException;

    Service findById(Long id) throws ResourceNotFoundException;

    int update(Service service) throws ParameterIsEmpty, ResourceNotFoundException;

    int delete(Service service) throws ParameterIsEmpty;
}
