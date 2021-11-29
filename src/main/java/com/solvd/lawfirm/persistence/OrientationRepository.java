package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.Orientation;

import java.util.List;

public interface OrientationRepository {

    void create(Orientation orientation);

    List<Orientation> findAll();

    Orientation findById(Long id);

    int update(Orientation orientation);

    int delete(Orientation orientation);
}
