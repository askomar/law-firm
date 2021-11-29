package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Judge;
import com.solvd.lawfirm.persistence.JudgeRepository;
import com.solvd.lawfirm.persistence.impl.JudgeRepositoryImpl;
import com.solvd.lawfirm.service.JudgeService;

import java.util.List;

public class JudgeServiceImpl implements JudgeService {

    private static final JudgeRepository JUDGE_REPOSITORY = JudgeRepositoryImpl.getInstance();

    @Override
    public void create(Judge judge) {
        JUDGE_REPOSITORY.create(judge);
    }

    @Override
    public List<Judge> findAll() {
        return JUDGE_REPOSITORY.findAll();
    }

    @Override
    public Judge findById(Long id) {
        return JUDGE_REPOSITORY.findById(id);
    }

    @Override
    public int update(Judge judge) {
        return JUDGE_REPOSITORY.update(judge);
    }

    @Override
    public int delete(Judge judge) {
        return JUDGE_REPOSITORY.delete(judge);
    }
}
