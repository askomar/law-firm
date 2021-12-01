package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.Service;

import java.util.List;

public interface ServiceRepository {

    void create(Service service);

    List<Service> findAll();

    Service findById(Long id);

    int update(Service service);

    int delete(Service service);
}
