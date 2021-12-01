package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.LawyerActivitySphere;

import java.util.List;

public interface LawyerActivitySphereService {

    void create(LawyerActivitySphere lawyerActivitySphere);

    List<LawyerActivitySphere> findAll();

    LawyerActivitySphere findById(Long id);

    int update(LawyerActivitySphere lawyerActivitySphere);

    int delete(LawyerActivitySphere lawyerActivitySphere);
}
