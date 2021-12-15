package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface ClientService {

    void create(Client client) throws ParameterIsEmpty;

    List<Client> findAll() throws ResourceNotFoundException;

    Client findById(Long id) throws ResourceNotFoundException;

    int update(Client client) throws ParameterIsEmpty;

    int delete(Client client) throws ParameterIsEmpty;
}
