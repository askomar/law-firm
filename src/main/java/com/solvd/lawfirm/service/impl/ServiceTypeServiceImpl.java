package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.ServiceType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ServiceTypeRepository;
import com.solvd.lawfirm.persistence.impl.mapper.ServiceTypeMapperImpl;
import com.solvd.lawfirm.service.ServiceTypeService;

import java.util.List;

public class ServiceTypeServiceImpl implements ServiceTypeService {

    private static final ServiceTypeRepository SERVICE_TYPE_REPOSITORY = new ServiceTypeMapperImpl();

    private static ServiceTypeService serviceTypeService;

    private ServiceTypeServiceImpl() {
    }

    public static ServiceTypeService getInstance() {
        if (serviceTypeService == null) {
            serviceTypeService = new ServiceTypeServiceImpl();
        }
        return serviceTypeService;
    }

    @Override
    public void create(ServiceType serviceType) throws ParameterIsEmpty {
        if (!isValid(serviceType)) {
            throw new ParameterIsEmpty("Service type's parameters are not specified");
        }
        SERVICE_TYPE_REPOSITORY.create(serviceType);
    }

    @Override
    public List<ServiceType> findAll() throws ResourceNotFoundException {
        List<ServiceType> serviceTypes = SERVICE_TYPE_REPOSITORY.findAll();
        if (serviceTypes.size() == 0) {
            throw new ResourceNotFoundException("Service type was not found");
        }
        return serviceTypes;
    }

    @Override
    public ServiceType findById(Long id) throws ResourceNotFoundException {
        ServiceType serviceType = SERVICE_TYPE_REPOSITORY.findById(id);
        if (serviceType == null) {
            throw new ResourceNotFoundException("Service type with id = " + id + " was not found");
        }
        return serviceType;
    }

    @Override
    public int update(ServiceType serviceType) throws ParameterIsEmpty {
        if (serviceType.getId() == null) {
            throw new ParameterIsEmpty("Can't update service type - there is no id");
        }
        if (!isValid(serviceType)) {
            throw new ParameterIsEmpty("Service type's parameters are not specified");
        }
        return SERVICE_TYPE_REPOSITORY.update(serviceType);
    }

    @Override
    public int delete(ServiceType serviceType) throws ParameterIsEmpty {
        if (serviceType.getId() == null) {
            throw new ParameterIsEmpty("Can't delete service type - there is no id");
        }
        return SERVICE_TYPE_REPOSITORY.delete(serviceType);
    }

    private boolean isValid(ServiceType serviceType) {
        return serviceType.getName() != null;
    }
}
