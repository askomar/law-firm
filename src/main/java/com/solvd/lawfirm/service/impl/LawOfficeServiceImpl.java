package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.LawOffice;
import com.solvd.lawfirm.persistence.LawOfficeRepository;
import com.solvd.lawfirm.persistence.impl.LawOfficeRepositoryImpl;
import com.solvd.lawfirm.service.LawOfficeService;

import java.util.List;

public class LawOfficeServiceImpl implements LawOfficeService {

    private static final LawOfficeRepository LAW_OFFICE_REPOSITORY = LawOfficeRepositoryImpl.getInstance();

    @Override
    public void create(LawOffice lawOffice) {
        LAW_OFFICE_REPOSITORY.create(lawOffice);
    }

    @Override
    public List<LawOffice> findAll() {
        return LAW_OFFICE_REPOSITORY.findAll();
    }

    @Override
    public LawOffice findById(Long id) {
        return LAW_OFFICE_REPOSITORY.findById(id);
    }

    @Override
    public int update(LawOffice lawOffice) {
        return LAW_OFFICE_REPOSITORY.update(lawOffice);
    }

    @Override
    public int delete(LawOffice lawOffice) {
        return LAW_OFFICE_REPOSITORY.delete(lawOffice);
    }
}
