package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.LawyerActivitySphere;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.LawyerActivitySphereRepository;
import com.solvd.lawfirm.persistence.impl.mapper.LawyerActivitySphereMapperImpl;
import com.solvd.lawfirm.service.LawyerActivitySphereService;

import java.util.List;

public class LawyerActivitySphereServiceImpl implements LawyerActivitySphereService {

    private static final LawyerActivitySphereRepository LAWYER_ACTIVITY_SPHERE_REPOSITORY = new LawyerActivitySphereMapperImpl();

    private static LawyerActivitySphereService lawyerActivitySphereService;

    private LawyerActivitySphereServiceImpl() {
    }

    public static LawyerActivitySphereService getInstance() {
        if (lawyerActivitySphereService == null) {
            lawyerActivitySphereService = new LawyerActivitySphereServiceImpl();
        }
        return lawyerActivitySphereService;
    }

    @Override
    public void create(LawyerActivitySphere lawyerActivitySphere) throws ParameterIsEmpty {
        if (!isValid(lawyerActivitySphere)) {
            throw new ParameterIsEmpty("Lawyer activity sphere's parameters are not specified");
        }
        LAWYER_ACTIVITY_SPHERE_REPOSITORY.create(lawyerActivitySphere);
    }

    @Override
    public List<LawyerActivitySphere> findAll() throws ResourceNotFoundException {
        List<LawyerActivitySphere> lawyerActivitySpheres = LAWYER_ACTIVITY_SPHERE_REPOSITORY.findAll();
        if (lawyerActivitySpheres.size() == 0) {
            throw new ResourceNotFoundException("Lawyer activity sphere was not found");
        }
        return lawyerActivitySpheres;
    }

    @Override
    public LawyerActivitySphere findById(Long id) throws ResourceNotFoundException {
        LawyerActivitySphere lawyerActivitySphere = LAWYER_ACTIVITY_SPHERE_REPOSITORY.findById(id);
        if (lawyerActivitySphere == null) {
            throw new ResourceNotFoundException("Lawyer activity sphere with id = " + id + " was not found");
        }
        return lawyerActivitySphere;
    }

    @Override
    public int update(LawyerActivitySphere lawyerActivitySphere) throws ParameterIsEmpty {
        if (lawyerActivitySphere.getId() == null) {
            throw new ParameterIsEmpty("Can't update lawyer activity sphere - there is no id");
        }
        if (!isValid(lawyerActivitySphere)) {
            throw new ParameterIsEmpty("Lawyer activity sphere's parameters are not specified");
        }
        return LAWYER_ACTIVITY_SPHERE_REPOSITORY.update(lawyerActivitySphere);
    }

    @Override
    public int delete(LawyerActivitySphere lawyerActivitySphere) throws ParameterIsEmpty {
        if (lawyerActivitySphere.getId() == null) {
            throw new ParameterIsEmpty("Can't delete lawyer activity sphere - there is no id");
        }
        return LAWYER_ACTIVITY_SPHERE_REPOSITORY.delete(lawyerActivitySphere);
    }

    boolean isValid(LawyerActivitySphere sphere) {
        return sphere.getName() != null;
    }
}
