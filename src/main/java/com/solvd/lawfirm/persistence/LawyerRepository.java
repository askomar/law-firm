package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.Lawyer;

import java.util.List;

public interface LawyerRepository {

    void create(Lawyer lawyer);

    List<Lawyer> findAll();

    Lawyer findById(Long id);

    int update(Lawyer lawyer);

    int delete(Lawyer lawyer);
}
