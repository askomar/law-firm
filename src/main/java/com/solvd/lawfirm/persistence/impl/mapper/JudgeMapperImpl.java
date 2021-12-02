package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Judge;
import com.solvd.lawfirm.persistence.JudgeRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class JudgeMapperImpl implements JudgeRepository {

    @Override
    public void create(Judge judge) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            JudgeRepository judgeRepository = session.getMapper(JudgeRepository.class);
            judgeRepository.create(judge);
        }
    }

    @Override
    public List<Judge> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            JudgeRepository judgeRepository = session.getMapper(JudgeRepository.class);
            return judgeRepository.findAll();
        }
    }

    @Override
    public Judge findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            JudgeRepository judgeRepository = session.getMapper(JudgeRepository.class);
            return judgeRepository.findById(id);
        }
    }

    @Override
    public int update(Judge judge) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            JudgeRepository judgeRepository = session.getMapper(JudgeRepository.class);
            return judgeRepository.update(judge);
        }
    }

    @Override
    public int delete(Judge judge) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            JudgeRepository judgeRepository = session.getMapper(JudgeRepository.class);
            return judgeRepository.delete(judge);
        }
    }
}
