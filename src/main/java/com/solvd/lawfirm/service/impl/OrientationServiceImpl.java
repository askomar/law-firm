package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.domain.LawyerActivitySphere;
import com.solvd.lawfirm.domain.Orientation;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.OrientationRepository;
import com.solvd.lawfirm.persistence.impl.mapper.OrientationMapperImpl;
import com.solvd.lawfirm.service.LawyerActivitySphereService;
import com.solvd.lawfirm.service.LawyerService;
import com.solvd.lawfirm.service.OrientationService;

import java.util.List;

public class OrientationServiceImpl implements OrientationService {

    private static final OrientationRepository ORIENTATION_REPOSITORY = new OrientationMapperImpl();
    private static final LawyerService LAWYER_SERVICE = LawyerServiceImpl.getInstance();
    private static final LawyerActivitySphereService LAWYER_ACTIVITY_SPHERE_SERVICE = LawyerActivitySphereServiceImpl.getInstance();

    private static OrientationService instance;

    private OrientationServiceImpl() {
    }

    public static OrientationService getInstance() {
        if (instance == null) {
            instance = new OrientationServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Orientation orientation, Long lawyerId, Long lawyerActivitySphereId) throws ParameterIsEmpty, ResourceNotFoundException {
        if (!isValid(orientation)) {
            throw new ParameterIsEmpty("Orientation parameters are not specified");
        }
        Lawyer lawyer = LAWYER_SERVICE.findById(lawyerId);
        LawyerActivitySphere lawyerActivitySphere = LAWYER_ACTIVITY_SPHERE_SERVICE.findById(lawyerActivitySphereId);
        orientation.setLawyer(lawyer);
        orientation.setLawyerActivitySphere(lawyerActivitySphere);
        ORIENTATION_REPOSITORY.create(orientation);
    }

    @Override
    public List<Orientation> findAll() throws ResourceNotFoundException {
        List<Orientation> orientations = ORIENTATION_REPOSITORY.findAll();
        if (orientations.size() == 0) {
            throw new ResourceNotFoundException("Orientations was not found");
        }
        return orientations;
    }

    @Override
    public Orientation findById(Long id) throws ResourceNotFoundException {
        Orientation orientation = ORIENTATION_REPOSITORY.findById(id);
        if (orientation == null) {
            throw new ResourceNotFoundException("Orientation with id = " + id + " was not found");
        }
        return orientation;
    }

    @Override
    public int update(Orientation orientation) throws ParameterIsEmpty, ResourceNotFoundException {
        if (orientation.getId() == null) {
            throw new ParameterIsEmpty("Can't update orientation - there is no id");
        }
        if (!isValid(orientation)) {
            throw new ParameterIsEmpty("Orientation parameters are not specified");
        }
        if (!orientation.getLawyerActivitySphere().equals(LAWYER_ACTIVITY_SPHERE_SERVICE.findById(orientation.getLawyerActivitySphere().getId()))) {
            LAWYER_ACTIVITY_SPHERE_SERVICE.update(orientation.getLawyerActivitySphere());
        }
        if (!orientation.getLawyer().equals(LAWYER_SERVICE.findById(orientation.getLawyer().getId()))) {
            LAWYER_SERVICE.update(orientation.getLawyer());
        }
        return ORIENTATION_REPOSITORY.update(orientation);
    }

    @Override
    public int delete(Orientation orientation) throws ParameterIsEmpty {
        if (orientation.getId() == null) {
            throw new ParameterIsEmpty("Can't delete orientation - there is no id");
        }
        return ORIENTATION_REPOSITORY.delete(orientation);
    }

    private boolean isValid(Orientation orientation) {
        return orientation.getLawyer() != null && orientation.getLawyerActivitySphere() != null;
    }
}
