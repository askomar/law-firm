package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Paperwork;

import java.util.List;

public interface PaperworkService {

    void create(Paperwork paperwork);

    List<Paperwork> findAll();

    Paperwork findById(Long id);

    int update(Paperwork paperwork);

    int delete(Paperwork paperwork);
}
