package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Judge;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.JudgeRepository;
import com.solvd.lawfirm.persistence.impl.mapper.JudgeMapperImpl;
import com.solvd.lawfirm.service.JudgeService;

import java.util.List;

public class JudgeServiceImpl implements JudgeService {

    private static final JudgeRepository JUDGE_REPOSITORY = new JudgeMapperImpl();

    private static JudgeService instance;

    private JudgeServiceImpl() {
    }

    public static JudgeService getInstance() {
        if (instance == null) {
            instance = new JudgeServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Judge judge) throws ParameterIsEmpty {
        if (!isValid(judge)) {
            throw new ParameterIsEmpty("Judge's one or more parameters are not specified");
        }
        JUDGE_REPOSITORY.create(judge);
    }

    @Override
    public List<Judge> findAll() throws ResourceNotFoundException {
        List<Judge> judges = JUDGE_REPOSITORY.findAll();
        if (judges.size() <= 0) {
            throw new ResourceNotFoundException("Judges was not found");
        }
        return judges;
    }

    @Override
    public Judge findById(Long id) throws ResourceNotFoundException {
        Judge judge = JUDGE_REPOSITORY.findById(id);
        if (judge == null) {
            throw new ResourceNotFoundException("Judge with id = " + id + " was not found");
        }
        return judge;
    }

    @Override
    public int update(Judge judge) throws ParameterIsEmpty {
        if (judge.getId() == null) {
            throw new ParameterIsEmpty("Can't update judge - there is no id");
        }
        if (!isValid(judge)) {
            throw new ParameterIsEmpty("Judge's one or more parameters are not specified");
        }
        return JUDGE_REPOSITORY.update(judge);
    }

    @Override
    public int delete(Judge judge) throws ParameterIsEmpty {
        if (judge.getId() == null) {
            throw new ParameterIsEmpty("Can't delete judge - there is no id");
        }
        return JUDGE_REPOSITORY.delete(judge);
    }

    private boolean isValid(Judge judge) {
        return judge.getSurname() != null && judge.getName() != null && judge.getDob() != null && judge.getExperienceSince() != null;
    }
}
