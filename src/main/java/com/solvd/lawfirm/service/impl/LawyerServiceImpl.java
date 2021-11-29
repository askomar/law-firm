package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.persistence.LawyerRepository;
import com.solvd.lawfirm.persistence.impl.LawyerRepositoryImpl;
import com.solvd.lawfirm.service.LawOfficeService;
import com.solvd.lawfirm.service.LawyerService;

import java.util.List;

public class LawyerServiceImpl implements LawyerService {

    private static final LawyerRepository LAWYER_REPOSITORY = LawyerRepositoryImpl.getInstance();
    private static final LawOfficeService LAW_OFFICE_SERVICE = new LawOfficeServiceImpl();

    @Override
    public void create(Lawyer lawyer) {
        if (lawyer.getLawOffice() != null && lawyer.getLawOffice().getId() == null) {
            LAW_OFFICE_SERVICE.create(lawyer.getLawOffice());
        }
        LAWYER_REPOSITORY.create(lawyer);
    }

    @Override
    public List<Lawyer> findAll() {
        return LAWYER_REPOSITORY.findAll();
    }

    @Override
    public Lawyer findById(Long id) {
        return LAWYER_REPOSITORY.findById(id);
    }

    @Override
    public int update(Lawyer lawyer) {
        return LAWYER_REPOSITORY.update(lawyer);
    }

    @Override
    public int delete(Lawyer lawyer) {
        return LAWYER_REPOSITORY.delete(lawyer);
    }
}
