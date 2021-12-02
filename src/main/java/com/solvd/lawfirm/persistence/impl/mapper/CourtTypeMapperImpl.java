package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.CourtType;
import com.solvd.lawfirm.persistence.CourtTypeRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CourtTypeMapperImpl implements CourtTypeRepository {

    @Override
    public void create(CourtType courtType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtTypeRepository courtTypeRepository = session.getMapper(CourtTypeRepository.class);
            courtTypeRepository.create(courtType);
        }
    }

    @Override
    public List<CourtType> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtTypeRepository courtTypeRepository = session.getMapper(CourtTypeRepository.class);
            return courtTypeRepository.findAll();
        }
    }

    @Override
    public CourtType findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtTypeRepository courtTypeRepository = session.getMapper(CourtTypeRepository.class);
            return courtTypeRepository.findById(id);
        }
    }

    @Override
    public int update(CourtType courtType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtTypeRepository courtTypeRepository = session.getMapper(CourtTypeRepository.class);
            return courtTypeRepository.update(courtType);
        }
    }

    @Override
    public int delete(CourtType courtType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            CourtTypeRepository courtTypeRepository = session.getMapper(CourtTypeRepository.class);
            return courtTypeRepository.delete(courtType);
        }
    }
}
