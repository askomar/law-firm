package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.CourtType;

import java.util.List;

public interface CourtTypeRepository {

    void create(CourtType courtType);

    List<CourtType> findAll();

    CourtType findById(Long id);

    int update(CourtType courtType);

    int delete(CourtType courtType);

}
