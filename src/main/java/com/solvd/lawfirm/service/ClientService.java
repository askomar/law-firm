package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.Client;

import java.util.List;

public interface ClientService {

    void create(Client client);

    List<Client> findAll();

    Client findById(Long id);

    int update(Client client);

    int delete(Client client);
}
