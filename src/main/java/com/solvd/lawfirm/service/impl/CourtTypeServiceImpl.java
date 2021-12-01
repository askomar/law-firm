package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.CourtType;
import com.solvd.lawfirm.persistence.CourtTypeRepository;
import com.solvd.lawfirm.persistence.impl.CourtTypeRepositoryImpl;
import com.solvd.lawfirm.service.CourtTypeService;

import java.util.List;

public class CourtTypeServiceImpl implements CourtTypeService {

    private static final CourtTypeRepository COURT_TYPE_REPOSITORY = CourtTypeRepositoryImpl.getInstance();

    @Override
    public void create(CourtType courtType) {
        COURT_TYPE_REPOSITORY.create(courtType);
    }

    @Override
    public List<CourtType> findAll() {
        return COURT_TYPE_REPOSITORY.findAll();
    }

    @Override
    public CourtType findById(Long id) {
        return COURT_TYPE_REPOSITORY.findById(id);
    }

    @Override
    public int update(CourtType courtType) {
        return COURT_TYPE_REPOSITORY.update(courtType);
    }

    @Override
    public int delete(CourtType courtType) {
        return COURT_TYPE_REPOSITORY.delete(courtType);
    }
}
