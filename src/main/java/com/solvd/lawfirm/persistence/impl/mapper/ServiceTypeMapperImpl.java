package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.ServiceType;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import com.solvd.lawfirm.persistence.ServiceTypeRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ServiceTypeMapperImpl implements ServiceTypeRepository {
    @Override
    public void create(ServiceType serviceType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceTypeRepository serviceTypeRepository = session.getMapper(ServiceTypeRepository.class);
            serviceTypeRepository.create(serviceType);
        }
    }

    @Override
    public List<ServiceType> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceTypeRepository serviceTypeRepository = session.getMapper(ServiceTypeRepository.class);
            return serviceTypeRepository.findAll();
        }

    }

    @Override
    public ServiceType findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceTypeRepository serviceTypeRepository = session.getMapper(ServiceTypeRepository.class);
            return serviceTypeRepository.findById(id);
        }
    }

    @Override
    public int update(ServiceType serviceType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceTypeRepository serviceTypeRepository = session.getMapper(ServiceTypeRepository.class);
            return serviceTypeRepository.update(serviceType);
        }
    }

    @Override
    public int delete(ServiceType serviceType) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ServiceTypeRepository serviceTypeRepository = session.getMapper(ServiceTypeRepository.class);
            return serviceTypeRepository.delete(serviceType);
        }
    }
}
