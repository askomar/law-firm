package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.PaperWorkType;
import com.solvd.lawfirm.persistence.PaperworkTypeRepository;
import com.solvd.lawfirm.persistence.impl.PaperworkTypeRepositoryImpl;
import com.solvd.lawfirm.service.PaperworkTypeService;

import java.util.List;

public class PaperworkTypeServiceImpl implements PaperworkTypeService {

    private static final PaperworkTypeRepository PAPERWORK_TYPE_REPOSITORY = PaperworkTypeRepositoryImpl.getInstance();

    @Override
    public void create(PaperWorkType paperWorkType) {
        PAPERWORK_TYPE_REPOSITORY.create(paperWorkType);
    }

    @Override
    public List<PaperWorkType> findAll() {
        return PAPERWORK_TYPE_REPOSITORY.findAll();
    }

    @Override
    public PaperWorkType findById(Long id) {
        return PAPERWORK_TYPE_REPOSITORY.findById(id);
    }

    @Override
    public int update(PaperWorkType paperWorkType) {
        return PAPERWORK_TYPE_REPOSITORY.update(paperWorkType);
    }

    @Override
    public int delete(PaperWorkType paperWorkType) {
        return PAPERWORK_TYPE_REPOSITORY.delete(paperWorkType);
    }
}
