package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.PaperWorkType;

import java.util.List;

public interface PaperworkTypeRepository {

    void create(PaperWorkType paperWorkType);

    List<PaperWorkType> findAll();

    PaperWorkType findById(Long id);

    int update(PaperWorkType paperWorkType);

    int delete(PaperWorkType paperWorkType);
}
