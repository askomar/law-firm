package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Court;
import com.solvd.lawfirm.persistence.CourtRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CourtMapperImpl implements CourtRepository {
    @Override
    public void create(Court court) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtRepository courtRepository = session.getMapper(CourtRepository.class);
            courtRepository.create(court);
        }
    }

    @Override
    public List<Court> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtRepository courtRepository = session.getMapper(CourtRepository.class);
            return courtRepository.findAll();
        }
    }

    @Override
    public Court findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtRepository courtRepository = session.getMapper(CourtRepository.class);
            return courtRepository.findById(id);
        }
    }

    @Override
    public int update(Court court) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtRepository courtRepository = session.getMapper(CourtRepository.class);
            return courtRepository.update(court);
        }
    }

    @Override
    public int delete(Court court) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtRepository courtRepository = session.getMapper(CourtRepository.class);
            return courtRepository.delete(court);
        }
    }
}
