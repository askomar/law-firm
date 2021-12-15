package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.PaperWorkType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.PaperworkTypeRepository;
import com.solvd.lawfirm.persistence.impl.mapper.PaperworkTypeMapperImpl;
import com.solvd.lawfirm.service.PaperworkTypeService;

import java.util.List;

public class PaperworkTypeServiceImpl implements PaperworkTypeService {

    private static final PaperworkTypeRepository PAPERWORK_TYPE_REPOSITORY = new PaperworkTypeMapperImpl();

    private static PaperworkTypeService instance;

    private PaperworkTypeServiceImpl() {
    }

    public static PaperworkTypeService getInstance() {
        if (instance == null) {
            instance = new PaperworkTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(PaperWorkType paperWorkType) throws ParameterIsEmpty {
        if (!isValid(paperWorkType)) {
            throw new ParameterIsEmpty("Paperwork type's parameters are not specified");
        }
        PAPERWORK_TYPE_REPOSITORY.create(paperWorkType);
    }

    @Override
    public List<PaperWorkType> findAll() throws ResourceNotFoundException {
        List<PaperWorkType> paperWorkTypes = PAPERWORK_TYPE_REPOSITORY.findAll();
        if (paperWorkTypes.size() == 0) {
            throw new ResourceNotFoundException("Paperwork type was not found");
        }
        return paperWorkTypes;
    }

    @Override
    public PaperWorkType findById(Long id) throws ResourceNotFoundException {
        PaperWorkType paperWorkType = PAPERWORK_TYPE_REPOSITORY.findById(id);
        if (paperWorkType == null) {
            throw new ResourceNotFoundException("Paperwork type with id = " + id + " was not found");
        }
        return paperWorkType;
    }

    @Override
    public int update(PaperWorkType paperWorkType) throws ParameterIsEmpty {
        if (paperWorkType.getId() == null) {
            throw new ParameterIsEmpty("Can't update paperwork type - there is no id");
        }
        if (!isValid(paperWorkType)) {
            throw new ParameterIsEmpty("Paperwork type's parameters are not specified");
        }
        return PAPERWORK_TYPE_REPOSITORY.update(paperWorkType);
    }

    @Override
    public int delete(PaperWorkType paperWorkType) throws ParameterIsEmpty {
        if (paperWorkType.getId() == null) {
            throw new ParameterIsEmpty("Can't delete paperwork type - there is no id");
        }
        return PAPERWORK_TYPE_REPOSITORY.delete(paperWorkType);
    }

    private boolean isValid(PaperWorkType paperWorkType) {
        return paperWorkType.getName() != null;
    }
}
