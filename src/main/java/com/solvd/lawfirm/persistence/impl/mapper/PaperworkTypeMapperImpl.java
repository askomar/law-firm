package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.PaperWorkType;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import com.solvd.lawfirm.persistence.PaperworkTypeRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PaperworkTypeMapperImpl implements PaperworkTypeRepository {
    @Override
    public void create(PaperWorkType paperWorkType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkTypeRepository paperworkTypeRepository = session.getMapper(PaperworkTypeRepository.class);
            paperworkTypeRepository.create(paperWorkType);
        }
    }

    @Override
    public List<PaperWorkType> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkTypeRepository paperworkTypeRepository = session.getMapper(PaperworkTypeRepository.class);
            return paperworkTypeRepository.findAll();
        }
    }

    @Override
    public PaperWorkType findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkTypeRepository paperworkTypeRepository = session.getMapper(PaperworkTypeRepository.class);
            return paperworkTypeRepository.findById(id);
        }
    }

    @Override
    public int update(PaperWorkType paperWorkType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkTypeRepository paperworkTypeRepository = session.getMapper(PaperworkTypeRepository.class);
            return paperworkTypeRepository.update(paperWorkType);
        }
    }

    @Override
    public int delete(PaperWorkType paperWorkType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkTypeRepository paperworkTypeRepository = session.getMapper(PaperworkTypeRepository.class);
            return paperworkTypeRepository.delete(paperWorkType);
        }
    }
}
