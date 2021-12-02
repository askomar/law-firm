package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.LawOffice;
import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.LawyerRepository;
import com.solvd.lawfirm.persistence.impl.mapper.LawyerMapperImpl;
import com.solvd.lawfirm.service.LawOfficeService;
import com.solvd.lawfirm.service.LawyerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

//TODO activitySpheres
public class LawyerServiceImpl implements LawyerService {

    private static final Logger logger = LogManager.getLogger(PaperworkServiceImpl.class);

    private static final LawyerRepository LAWYER_REPOSITORY = new LawyerMapperImpl();
    private static final LawOfficeService LAW_OFFICE_SERVICE = new LawOfficeServiceImpl();

    private static LawyerService lawyerService;

    private LawyerServiceImpl() {
    }

    public static LawyerService getInstance() {
        if (lawyerService == null) {
            lawyerService = new LawyerServiceImpl();
        }
        return lawyerService;
    }

    @Override
    public void create(Lawyer lawyer, Long officeId) throws ParameterIsEmpty, ResourceNotFoundException {
        if (!isValid(lawyer)) {
            throw new ParameterIsEmpty("Lawyer's one or more parameters are not specified");
        }
        LawOffice lawOffice = LAW_OFFICE_SERVICE.findById(officeId);
        lawyer.setLawOffice(lawOffice);
        LAWYER_REPOSITORY.create(lawyer);
    }

    @Override
    public List<Lawyer> findAll() throws ResourceNotFoundException {
        List<Lawyer> lawyers = LAWYER_REPOSITORY.findAll();
        if (lawyers.size() <= 0) {
            throw new ResourceNotFoundException("Lawyers was not found");
        }
        lawyers.forEach(lawyer -> {
            try {
                lawyer.setLawOffice(LAW_OFFICE_SERVICE.findById(lawyer.getLawOffice().getId()));
            } catch (ResourceNotFoundException e) {
                logger.error("Exception when try to initialise findAll query by lawyers");
            }
        });
        return lawyers;
    }

    @Override
    public Lawyer findById(Long id) throws ResourceNotFoundException {
        Lawyer lawyer = LAWYER_REPOSITORY.findById(id);
        if (lawyer == null) {
            throw new ResourceNotFoundException("Lawyer with id = " + id + " was not found");
        }
        lawyer.setLawOffice(LAW_OFFICE_SERVICE.findById(lawyer.getLawOffice().getId()));
        return lawyer;
    }

    @Override
    public int update(Lawyer lawyer) throws ParameterIsEmpty, ResourceNotFoundException {
        if (lawyer.getId() == null) {
            throw new ParameterIsEmpty("Can't update lawyer - there is no id");
        }
        if (!isValid(lawyer)) {
            throw new ParameterIsEmpty("Lawyer's one or more parameters are not specified");
        }
        if (!lawyer.getLawOffice().equals(LAW_OFFICE_SERVICE.findById(lawyer.getLawOffice().getId()))) {
            LAW_OFFICE_SERVICE.update(lawyer.getLawOffice());
        }
        return LAWYER_REPOSITORY.update(lawyer);
    }

    @Override
    public int delete(Lawyer lawyer) throws ParameterIsEmpty {
        if (lawyer.getId() == null) {
            throw new ParameterIsEmpty("Can't delete lawyer - there is no id");
        }
        return LAWYER_REPOSITORY.delete(lawyer);
    }

    private boolean isValid(Lawyer lawyer) {
        return lawyer.getLawOffice() != null && lawyer.getSurname() != null && lawyer.getName() != null && lawyer.getDob() != null && lawyer.getExperienceSince() != null;
    }
}
