package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Judge;

import java.util.List;

public interface JudgeService {

    void create(Judge judge);

    List<Judge> findAll();

    Judge findById(Long id);

    int update(Judge judge);

    int delete(Judge judge);
}
