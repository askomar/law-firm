package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Lawyer;

import java.util.List;

public interface LawyerService {

    void create(Lawyer lawyer);

    List<Lawyer> findAll();

    Lawyer findById(Long id);

    int update(Lawyer lawyer);

    int delete(Lawyer lawyer);
}
