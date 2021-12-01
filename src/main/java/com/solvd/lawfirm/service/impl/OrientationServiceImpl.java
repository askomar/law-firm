package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Orientation;
import com.solvd.lawfirm.persistence.OrientationRepository;
import com.solvd.lawfirm.persistence.impl.OrientationRepositoryImpl;
import com.solvd.lawfirm.service.LawyerActivitySphereService;
import com.solvd.lawfirm.service.LawyerService;
import com.solvd.lawfirm.service.OrientationService;

import java.util.List;

public class OrientationServiceImpl implements OrientationService {

    private static final OrientationRepository ORIENTATION_REPOSITORY = OrientationRepositoryImpl.getInstance();
    private static final LawyerService LAWYER_SERVICE = new LawyerServiceImpl();
    private static final LawyerActivitySphereService LAWYER_ACTIVITY_SPHERE_SERVICE = new LawyerActivitySphereServiceImpl();

    @Override
    public void create(Orientation orientation) {
        if (orientation.getLawyer() != null && (orientation.getLawyer().getId() == null)) {
            LAWYER_SERVICE.create(orientation.getLawyer());
        }
        if (orientation.getLawyerActivitySphere() != null && (orientation.getLawyerActivitySphere().getId() == null)) {
            LAWYER_ACTIVITY_SPHERE_SERVICE.create(orientation.getLawyerActivitySphere());
        }
        ORIENTATION_REPOSITORY.create(orientation);
    }

    @Override
    public List<Orientation> findAll() {
        return ORIENTATION_REPOSITORY.findAll();
    }

    @Override
    public Orientation findById(Long id) {
        return ORIENTATION_REPOSITORY.findById(id);
    }

    @Override
    public int update(Orientation orientation) {
        return ORIENTATION_REPOSITORY.update(orientation);
    }

    @Override
    public int delete(Orientation orientation) {
        return ORIENTATION_REPOSITORY.delete(orientation);
    }
}
