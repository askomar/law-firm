package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Court;
import com.solvd.lawfirm.domain.CourtType;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.CourtRepository;
import com.solvd.lawfirm.persistence.impl.mapper.CourtMapperImpl;
import com.solvd.lawfirm.service.CourtService;
import com.solvd.lawfirm.service.CourtTypeService;

import java.util.List;

public class CourtServiceImpl implements CourtService {

    private static final CourtRepository COURT_REPOSITORY = new CourtMapperImpl();
    private static final CourtTypeService COURT_TYPE_SERVICE = CourtTypeServiceImpl.getInstance();

    private static CourtService instance;

    private CourtServiceImpl() {
    }

    public static CourtService getInstance() {
        if (instance == null) {
            instance = new CourtServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Court court, Long courtTypeId) throws ParameterIsEmpty, ResourceNotFoundException {
        if (!isValid(court)) {
            throw new ParameterIsEmpty("Court's one or more parameters are not specified");
        }
        CourtType courtType = COURT_TYPE_SERVICE.findById(courtTypeId);
        court.setType(courtType);
        COURT_REPOSITORY.create(court);
    }

    @Override
    public List<Court> findAll() throws ResourceNotFoundException {
        List<Court> courts = COURT_REPOSITORY.findAll();
        if (courts.size() <= 0) {
            throw new ResourceNotFoundException("Courts was not found");
        }
        return courts;
    }

    @Override
    public Court findById(Long id) throws ResourceNotFoundException {
        Court court = COURT_REPOSITORY.findById(id);
        if (court == null) {
            throw new ResourceNotFoundException("Court with id = " + id + " was not found");
        }
        court.setType(COURT_TYPE_SERVICE.findById(court.getType().getId()));
        return court;
    }

    @Override
    public int update(Court court) throws ParameterIsEmpty, ResourceNotFoundException {
        if (court.getId() == null) {
            throw new ParameterIsEmpty("Can't update court - there is no id");
        }
        if (!isValid(court)) {
            throw new ParameterIsEmpty("Court's one or more parameters are not specified");
        }
        if (!court.getType().equals(COURT_TYPE_SERVICE.findById(court.getType().getId()))) {
            COURT_TYPE_SERVICE.update(court.getType());
        }
        return COURT_REPOSITORY.update(court);
    }

    @Override
    public int delete(Court court) throws ParameterIsEmpty {
        if (court.getId() == null) {
            throw new ParameterIsEmpty("Can't delete court - there is no id");
        }
        return COURT_REPOSITORY.delete(court);
    }

    private boolean isValid(Court court) {
        return court.getName() != null && court.getAddress() != null && court.getType() != null;
    }
}
