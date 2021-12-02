package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.LawOffice;
import com.solvd.lawfirm.persistence.LawOfficeRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LawOfficeMapperImpl implements LawOfficeRepository {

    @Override
    public void create(LawOffice lawOffice) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawOfficeRepository lawOfficeRepository = session.getMapper(LawOfficeRepository.class);
            lawOfficeRepository.create(lawOffice);
        }
    }

    @Override
    public List<LawOffice> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawOfficeRepository lawOfficeRepository = session.getMapper(LawOfficeRepository.class);
            return lawOfficeRepository.findAll();
        }
    }

    @Override
    public LawOffice findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawOfficeRepository lawOfficeRepository = session.getMapper(LawOfficeRepository.class);
            return lawOfficeRepository.findById(id);
        }
    }

    @Override
    public int update(LawOffice lawOffice) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawOfficeRepository lawOfficeRepository = session.getMapper(LawOfficeRepository.class);
            return lawOfficeRepository.update(lawOffice);
        }
    }

    @Override
    public int delete(LawOffice lawOffice) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawOfficeRepository lawOfficeRepository = session.getMapper(LawOfficeRepository.class);
            return lawOfficeRepository.delete(lawOffice);
        }
    }
}
