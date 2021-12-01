package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Court;

import java.util.List;

public interface CourtService {

    void create(Court court);

    List<Court> findAll();

    Court findById(Long id);

    int update(Court court);

    int delete(Court court);
}
