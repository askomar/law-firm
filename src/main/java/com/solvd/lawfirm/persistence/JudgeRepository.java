package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.Judge;

import java.util.List;

public interface JudgeRepository {

    void create(Judge judge);

    List<Judge> findAll();

    Judge findById(Long id);

    int update(Judge judge);

    int delete(Judge judge);
}
