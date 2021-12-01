package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Court;
import com.solvd.lawfirm.persistence.CourtRepository;
import com.solvd.lawfirm.persistence.impl.CourtRepositoryImpl;
import com.solvd.lawfirm.service.CourtService;
import com.solvd.lawfirm.service.CourtTypeService;

import java.util.List;

public class CourtServiceImpl implements CourtService {

    private static final CourtRepository COURT_REPOSITORY = CourtRepositoryImpl.getInstance();
    private static final CourtTypeService COURT_TYPE_SERVICE = new CourtTypeServiceImpl();

    @Override
    public void create(Court court) {
        if (court.getType() != null && (court.getType().getId() == null)) {
            COURT_TYPE_SERVICE.create(court.getType());
        }
        COURT_REPOSITORY.create(court);
    }

    @Override
    public List<Court> findAll() {
        return COURT_REPOSITORY.findAll();
    }

    @Override
    public Court findById(Long id) {
        return COURT_REPOSITORY.findById(id);
    }

    @Override
    public int update(Court court) {
        return COURT_REPOSITORY.update(court);
    }

    @Override
    public int delete(Court court) {
        return COURT_REPOSITORY.delete(court);
    }
}
