package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.ServiceType;

import java.util.List;

public interface ServiceTypeRepository {

    void create(ServiceType serviceType);

    List<ServiceType> findAll();

    ServiceType findById(Long id);

    int update(ServiceType serviceType);

    int delete(ServiceType serviceType);
}
