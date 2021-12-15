package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.persistence.LawyerRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LawyerMapperImpl implements LawyerRepository {
    @Override
    public void create(Lawyer lawyer) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerRepository lawyerRepository = session.getMapper(LawyerRepository.class);
            lawyerRepository.create(lawyer);
        }
    }

    @Override
    public List<Lawyer> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerRepository lawyerRepository = session.getMapper(LawyerRepository.class);
            return lawyerRepository.findAll();
        }
    }

    @Override
    public Lawyer findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerRepository lawyerRepository = session.getMapper(LawyerRepository.class);
            return lawyerRepository.findById(id);
        }
    }

    @Override
    public int update(Lawyer lawyer) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerRepository lawyerRepository = session.getMapper(LawyerRepository.class);
            return lawyerRepository.update(lawyer);
        }
    }

    @Override
    public int delete(Lawyer lawyer) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerRepository lawyerRepository = session.getMapper(LawyerRepository.class);
            return lawyerRepository.delete(lawyer);
        }
    }
}
