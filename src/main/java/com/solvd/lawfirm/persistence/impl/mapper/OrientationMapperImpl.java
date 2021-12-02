package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Orientation;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import com.solvd.lawfirm.persistence.OrientationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrientationMapperImpl implements OrientationRepository {
    @Override
    public void create(Orientation orientation) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            OrientationRepository orientationRepository = session.getMapper(OrientationRepository.class);
            orientationRepository.create(orientation);
        }
    }

    @Override
    public List<Orientation> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            OrientationRepository orientationRepository = session.getMapper(OrientationRepository.class);
            return orientationRepository.findAll();
        }
    }

    @Override
    public Orientation findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            OrientationRepository orientationRepository = session.getMapper(OrientationRepository.class);
            return orientationRepository.findById(id);
        }
    }

    @Override
    public int update(Orientation orientation) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            OrientationRepository orientationRepository = session.getMapper(OrientationRepository.class);
            return orientationRepository.update(orientation);
        }
    }

    @Override
    public int delete(Orientation orientation) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            OrientationRepository orientationRepository = session.getMapper(OrientationRepository.class);
            return orientationRepository.delete(orientation);
        }
    }
}
