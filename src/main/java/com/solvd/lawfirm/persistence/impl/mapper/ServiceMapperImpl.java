package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Service;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import com.solvd.lawfirm.persistence.ServiceRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ServiceMapperImpl implements ServiceRepository {
    @Override
    public void create(Service service) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            serviceRepository.create(service);
        }
    }

    @Override
    public List<Service> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            return serviceRepository.findAll();
        }
    }

    @Override
    public Service findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            return serviceRepository.findById(id);
        }
    }

    @Override
    public int update(Service service) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            return serviceRepository.update(service);
        }
    }

    @Override
    public int delete(Service service) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = session.getMapper(ServiceRepository.class);
            return serviceRepository.delete(service);
        }
    }
}
