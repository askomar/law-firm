package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.LawyerActivitySphere;
import com.solvd.lawfirm.persistence.LawyerActivitySphereRepository;
import com.solvd.lawfirm.persistence.impl.LawyerActivitySphereRepositoryImpl;
import com.solvd.lawfirm.service.LawyerActivitySphereService;

import java.util.List;

public class LawyerActivitySphereServiceImpl implements LawyerActivitySphereService {

    private static final LawyerActivitySphereRepository LAWYER_ACTIVITY_SPHERE_REPOSITORY = LawyerActivitySphereRepositoryImpl.getInstance();

    @Override
    public void create(LawyerActivitySphere lawyerActivitySphere) {
        LAWYER_ACTIVITY_SPHERE_REPOSITORY.create(lawyerActivitySphere);
    }

    @Override
    public List<LawyerActivitySphere> findAll() {
        return LAWYER_ACTIVITY_SPHERE_REPOSITORY.findAll();
    }

    @Override
    public LawyerActivitySphere findById(Long id) {
        return LAWYER_ACTIVITY_SPHERE_REPOSITORY.findById(id);
    }

    @Override
    public int update(LawyerActivitySphere lawyerActivitySphere) {
        return LAWYER_ACTIVITY_SPHERE_REPOSITORY.update(lawyerActivitySphere);
    }

    @Override
    public int delete(LawyerActivitySphere lawyerActivitySphere) {
        return LAWYER_ACTIVITY_SPHERE_REPOSITORY.delete(lawyerActivitySphere);
    }
}
