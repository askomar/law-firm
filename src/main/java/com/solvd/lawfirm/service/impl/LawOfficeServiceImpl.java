package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.LawOffice;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.LawOfficeRepository;
import com.solvd.lawfirm.persistence.impl.mapper.LawOfficeMapperImpl;
import com.solvd.lawfirm.service.LawOfficeService;

import java.util.List;

public class LawOfficeServiceImpl implements LawOfficeService {

    private static LawOfficeServiceImpl INSTANCE;
    private static final LawOfficeRepository LAW_OFFICE_REPOSITORY = new LawOfficeMapperImpl();

    public static LawOfficeService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LawOfficeServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public void create(LawOffice lawOffice) throws ParameterIsEmpty {
        if (!isValid(lawOffice)) {
            throw new ParameterIsEmpty("Law office's parameters are not specified");
        }
        LAW_OFFICE_REPOSITORY.create(lawOffice);
    }

    @Override
    public List<LawOffice> findAll() throws ResourceNotFoundException {
        List<LawOffice> lawOffices = LAW_OFFICE_REPOSITORY.findAll();
        if (lawOffices.size() == 0) {
            throw new ResourceNotFoundException("Law offices was not found");
        }
        return lawOffices;
    }

    @Override
    public LawOffice findById(Long id) throws ResourceNotFoundException {
        LawOffice lawOffice = LAW_OFFICE_REPOSITORY.findById(id);
        if (lawOffice == null) {
            throw new ResourceNotFoundException("Law office with id = " + id + " was not found");
        }
        return lawOffice;
    }

    @Override
    public int update(LawOffice lawOffice) throws ParameterIsEmpty {
        if (lawOffice.getId() == null) {
            throw new ParameterIsEmpty("Can't update law office - there is no id");
        }
        if (!isValid(lawOffice)) {
            throw new ParameterIsEmpty("Law office's name or address are not specified");
        }
        return LAW_OFFICE_REPOSITORY.update(lawOffice);
    }

    @Override
    public int delete(LawOffice lawOffice) throws ParameterIsEmpty {
        if (lawOffice.getId() == null) {
            throw new ParameterIsEmpty("Can't delete law office - there is no id");
        }
        return LAW_OFFICE_REPOSITORY.delete(lawOffice);
    }

    private boolean isValid(LawOffice lawOffice) {
        return lawOffice.getName() != null && lawOffice.getAddress() != null;
    }
}
