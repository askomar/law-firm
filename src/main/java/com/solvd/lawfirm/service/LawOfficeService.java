package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.LawOffice;

import java.util.List;

public interface LawOfficeService {

    void create(LawOffice lawOffice);

    List<LawOffice> findAll();

    LawOffice findById(Long id);

    int update(LawOffice lawOffice);

    int delete(LawOffice lawOffice);
}
