package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.domain.Paperwork;
import com.solvd.lawfirm.domain.Service;
import com.solvd.lawfirm.domain.ServiceType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ServiceRepository;
import com.solvd.lawfirm.persistence.impl.mapper.ServiceMapperImpl;
import com.solvd.lawfirm.service.LawyerService;
import com.solvd.lawfirm.service.PaperworkService;
import com.solvd.lawfirm.service.ServiceService;
import com.solvd.lawfirm.service.ServiceTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger = LogManager.getLogger(ServiceServiceImpl.class);

    private static final ServiceRepository SERVICE_REPOSITORY = new ServiceMapperImpl();
    private static final ServiceTypeService SERVICE_TYPE_SERVICE = ServiceTypeServiceImpl.getInstance();
    private static final LawyerService LAWYER_SERVICE = LawyerServiceImpl.getInstance();
    private static final PaperworkService PAPERWORK_SERVICE = PaperworkServiceImpl.getInstance();

    private static ServiceService instance;

    private ServiceServiceImpl() {
    }

    public static ServiceService getInstance() {
        if (instance == null) {
            instance = new ServiceServiceImpl();
        }
        return instance;
    }

    public void create(Service service, Long serviceTypeId, Long lawyerId) throws ParameterIsEmpty, ResourceNotFoundException {
        create(service, serviceTypeId, lawyerId, null);
    }

    @Override
    public void create(Service service, Long serviceTypeId, Long lawyerId, Long paperworkId) throws ParameterIsEmpty, ResourceNotFoundException {
        if (!isValid(service)) {
            throw new ParameterIsEmpty("Service's one or more parameters are not specified");
        }

        ServiceType serviceType = SERVICE_TYPE_SERVICE.findById(serviceTypeId);
        Lawyer lawyer = LAWYER_SERVICE.findById(lawyerId);
        Paperwork paperwork = null;
        if (paperworkId != null) {
            paperwork = PAPERWORK_SERVICE.findById(paperworkId);
        }
        service.setType(serviceType);
        service.setLawyer(lawyer);
        service.setPaperWork(paperwork);
        SERVICE_REPOSITORY.create(service);
    }

    @Override
    public List<Service> findAll() throws ResourceNotFoundException {
        List<Service> services = SERVICE_REPOSITORY.findAll();
        if (services.size() <= 0) {
            throw new ResourceNotFoundException("Services was not found");
        }
        services.forEach(service -> {
            try {
                ServiceType serviceType = SERVICE_TYPE_SERVICE.findById(service.getType().getId());

                Lawyer lawyer = LAWYER_SERVICE.findById(service.getLawyer().getId());
                Paperwork paperwork = null;
                if (service.getPaperWork() != null) {
                    paperwork = PAPERWORK_SERVICE.findById(service.getPaperWork().getId());
                }
                service.setType(serviceType);
                service.setLawyer(lawyer);
                service.setPaperWork(paperwork);

            } catch (ResourceNotFoundException e) {
                logger.error("Exception when try to initialise findAll query by services");

            }
        });
        return services;
    }

    @Override
    public Service findById(Long id) throws ResourceNotFoundException {
        Service service = SERVICE_REPOSITORY.findById(id);
        if (service == null) {
            throw new ResourceNotFoundException("Service with id = " + id + " was not found");
        }
        service.setType(SERVICE_TYPE_SERVICE.findById(service.getType().getId()));
        service.setLawyer(LAWYER_SERVICE.findById(service.getLawyer().getId()));
        if (service.getPaperWork() != null) {
            service.setPaperWork(PAPERWORK_SERVICE.findById(service.getPaperWork().getId()));
        }
        return service;
    }

    @Override
    public int update(Service service) throws ParameterIsEmpty, ResourceNotFoundException {
        if (service.getId() == null) {
            throw new ParameterIsEmpty("Can't update service - there is no id");
        }
        if (!isValid(service)) {
            throw new ParameterIsEmpty("Paperwork's one or more parameters are not specified");
        }
        if (!service.getType().equals(SERVICE_TYPE_SERVICE.findById(service.getType().getId()))) {
            SERVICE_TYPE_SERVICE.update(service.getType());
        }
        if (!service.getLawyer().equals(LAWYER_SERVICE.findById(service.getLawyer().getId()))) {
            LAWYER_SERVICE.update(service.getLawyer());
        }
        if (service.getPaperWork() != null) {
            if (!service.getPaperWork().equals(PAPERWORK_SERVICE.findById(service.getPaperWork().getId()))) {
                PAPERWORK_SERVICE.update(service.getPaperWork());
            }
        }
        return SERVICE_REPOSITORY.update(service);
    }

    @Override
    public int delete(Service service) throws ParameterIsEmpty {
        if (service.getId() == null) {
            throw new ParameterIsEmpty("Can't delete paperwork - there is no id");
        }
        return SERVICE_REPOSITORY.delete(service);
    }

    boolean isValid(Service service) {
        return service.getType() != null && service.getLawyer() != null && service.getCost() != null;
    }
}
