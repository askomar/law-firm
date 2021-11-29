package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Orientation;

import java.util.List;

public interface OrientationService {

    void create(Orientation orientation);

    List<Orientation> findAll();

    Orientation findById(Long id);

    int update(Orientation orientation);

    int delete(Orientation orientation);
}
