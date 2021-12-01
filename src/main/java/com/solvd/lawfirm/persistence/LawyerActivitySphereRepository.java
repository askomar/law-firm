package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.LawyerActivitySphere;

import java.util.List;

public interface LawyerActivitySphereRepository {

    void create(LawyerActivitySphere lawyerActivitySphere);

    List<LawyerActivitySphere> findAll();

    LawyerActivitySphere findById(Long id);

    int update(LawyerActivitySphere lawyerActivitySphere);

    int delete(LawyerActivitySphere lawyerActivitySphere);
}
