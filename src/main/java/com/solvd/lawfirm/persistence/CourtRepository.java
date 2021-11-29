package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.Court;

import java.util.List;

public interface CourtRepository {

    void create(Court court);

    List<Court> findAll();

    Court findById(Long id);

    int update(Court court);

    int delete(Court court);
}
