package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.ServiceType;

import java.util.List;

public interface ServiceTypeService {

    void create(ServiceType serviceType);

    List<ServiceType> findAll();

    ServiceType findById(Long id);

    int update(ServiceType serviceType);

    int delete(ServiceType serviceType);
}
