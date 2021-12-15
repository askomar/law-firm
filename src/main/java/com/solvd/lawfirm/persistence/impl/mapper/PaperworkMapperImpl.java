package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Paperwork;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import com.solvd.lawfirm.persistence.PaperworkRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PaperworkMapperImpl implements PaperworkRepository {
    @Override
    public void create(Paperwork paperwork) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkRepository paperworkRepository = session.getMapper(PaperworkRepository.class);
            paperworkRepository.create(paperwork);
        }
    }

    @Override
    public List<Paperwork> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkRepository paperworkRepository = session.getMapper(PaperworkRepository.class);
            return paperworkRepository.findAll();
        }
    }

    @Override
    public Paperwork findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkRepository paperworkRepository = session.getMapper(PaperworkRepository.class);
            return paperworkRepository.findById(id);
        }
    }

    @Override
    public int update(Paperwork paperwork) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkRepository paperworkRepository = session.getMapper(PaperworkRepository.class);
            return paperworkRepository.update(paperwork);
        }
    }

    @Override
    public int delete(Paperwork paperwork) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PaperworkRepository paperworkRepository = session.getMapper(PaperworkRepository.class);
            return paperworkRepository.delete(paperwork);
        }
    }
}
