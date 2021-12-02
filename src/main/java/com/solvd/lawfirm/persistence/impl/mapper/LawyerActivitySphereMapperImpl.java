package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.LawyerActivitySphere;
import com.solvd.lawfirm.persistence.LawyerActivitySphereRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LawyerActivitySphereMapperImpl implements LawyerActivitySphereRepository {
    @Override
    public void create(LawyerActivitySphere lawyerActivitySphere) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerActivitySphereRepository lawyerActivitySphereRepository = session.getMapper(LawyerActivitySphereRepository.class);
            lawyerActivitySphereRepository.create(lawyerActivitySphere);
        }
    }

    @Override
    public List<LawyerActivitySphere> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerActivitySphereRepository lawyerActivitySphereRepository = session.getMapper(LawyerActivitySphereRepository.class);
            return lawyerActivitySphereRepository.findAll();
        }
    }

    @Override
    public LawyerActivitySphere findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerActivitySphereRepository lawyerActivitySphereRepository = session.getMapper(LawyerActivitySphereRepository.class);
            return lawyerActivitySphereRepository.findById(id);
        }
    }

    @Override
    public int update(LawyerActivitySphere lawyerActivitySphere) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerActivitySphereRepository lawyerActivitySphereRepository = session.getMapper(LawyerActivitySphereRepository.class);
            return lawyerActivitySphereRepository.update(lawyerActivitySphere);
        }
    }

    @Override
    public int delete(LawyerActivitySphere lawyerActivitySphere) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            LawyerActivitySphereRepository lawyerActivitySphereRepository = session.getMapper(LawyerActivitySphereRepository.class);
            return lawyerActivitySphereRepository.delete(lawyerActivitySphere);
        }
    }
}
