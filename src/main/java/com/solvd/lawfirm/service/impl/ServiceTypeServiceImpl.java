package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.ServiceType;
import com.solvd.lawfirm.persistence.ServiceTypeRepository;
import com.solvd.lawfirm.persistence.impl.ServiceTypeRepositoryImpl;
import com.solvd.lawfirm.service.ServiceTypeService;

import java.util.List;

public class ServiceTypeServiceImpl implements ServiceTypeService {

    private static final ServiceTypeRepository SERVICE_TYPE_REPOSITORY = ServiceTypeRepositoryImpl.getInstance();

    @Override
    public void create(ServiceType serviceType) {
        SERVICE_TYPE_REPOSITORY.create(serviceType);
    }

    @Override
    public List<ServiceType> findAll() {
        return SERVICE_TYPE_REPOSITORY.findAll();
    }

    @Override
    public ServiceType findById(Long id) {
        return SERVICE_TYPE_REPOSITORY.findById(id);
    }

    @Override
    public int update(ServiceType serviceType) {
        return SERVICE_TYPE_REPOSITORY.update(serviceType);
    }

    @Override
    public int delete(ServiceType serviceType) {
        return SERVICE_TYPE_REPOSITORY.delete(serviceType);
    }
}
