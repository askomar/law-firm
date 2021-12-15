package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.CourtType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.CourtTypeRepository;
import com.solvd.lawfirm.persistence.impl.mapper.CourtTypeMapperImpl;
import com.solvd.lawfirm.service.CourtTypeService;

import java.util.List;

public class CourtTypeServiceImpl implements CourtTypeService {

    private static final CourtTypeRepository COURT_TYPE_REPOSITORY = new CourtTypeMapperImpl();

    private static CourtTypeService instance;

    private CourtTypeServiceImpl() {
    }

    public static CourtTypeService getInstance() {
        if (instance == null) {
            instance = new CourtTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(CourtType courtType) throws ParameterIsEmpty {
        if (!isValid(courtType)) {
            throw new ParameterIsEmpty("Court type parameters are not specified");
        }
        COURT_TYPE_REPOSITORY.create(courtType);
    }

    @Override
    public List<CourtType> findAll() throws ResourceNotFoundException {
        List<CourtType> courtTypes = COURT_TYPE_REPOSITORY.findAll();
        if (courtTypes.size() == 0) {
            throw new ResourceNotFoundException("Court types was not found");
        }
        return courtTypes;
    }

    @Override
    public CourtType findById(Long id) throws ResourceNotFoundException {
        CourtType courtType = COURT_TYPE_REPOSITORY.findById(id);
        if (courtType == null) {
            throw new ResourceNotFoundException("Court type with id = " + id + " was not found");
        }
        return courtType;
    }

    @Override
    public int update(CourtType courtType) throws ParameterIsEmpty {
        if (courtType.getId() == null) {
            throw new ParameterIsEmpty("Can't update court type - there is no id");
        }
        if (!isValid(courtType)) {
            throw new ParameterIsEmpty("Court type parameters are not specified");
        }
        return COURT_TYPE_REPOSITORY.update(courtType);
    }

    @Override
    public int delete(CourtType courtType) throws ParameterIsEmpty {
        if (courtType.getId() == null) {
            throw new ParameterIsEmpty("Can't delete court type - there is no id");
        }
        return COURT_TYPE_REPOSITORY.delete(courtType);
    }

    boolean isValid(CourtType courtType) {
        return courtType.getName() != null;
    }
}
