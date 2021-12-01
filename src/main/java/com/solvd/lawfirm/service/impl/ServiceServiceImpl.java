package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Service;
import com.solvd.lawfirm.persistence.ServiceRepository;
import com.solvd.lawfirm.persistence.impl.ServiceRepositoryImpl;
import com.solvd.lawfirm.service.LawyerService;
import com.solvd.lawfirm.service.PaperworkService;
import com.solvd.lawfirm.service.ServiceService;
import com.solvd.lawfirm.service.ServiceTypeService;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    private static final ServiceRepository SERVICE_REPOSITORY = ServiceRepositoryImpl.getInstance();
    private static final ServiceTypeService SERVICE_TYPE_SERVICE = new ServiceTypeServiceImpl();
    private static final LawyerService LAWYER_SERVICE = new LawyerServiceImpl();
    private static final PaperworkService PAPERWORK_SERVICE = new PaperworkServiceImpl();

    @Override
    public void create(Service service) {
        if (service.getType() != null && (service.getType().getId() == null)) {
            SERVICE_TYPE_SERVICE.create(service.getType());
        }
        if (service.getLawyer() != null && (service.getLawyer().getId() == null)) {
            LAWYER_SERVICE.create(service.getLawyer());
        }
        if (service.getPaperWork() != null && (service.getPaperWork().getId() == null)) {
            PAPERWORK_SERVICE.create(service.getPaperWork());
        }
        SERVICE_REPOSITORY.create(service);
    }

    @Override
    public List<Service> findAll() {
        return SERVICE_REPOSITORY.findAll();
    }

    @Override
    public Service findById(Long id) {
        return SERVICE_REPOSITORY.findById(id);
    }

    @Override
    public int update(Service service) {
        return SERVICE_REPOSITORY.update(service);
    }

    @Override
    public int delete(Service service) {
        return SERVICE_REPOSITORY.delete(service);
    }
}
