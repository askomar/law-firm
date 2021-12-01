package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.Client;

import java.util.List;

public interface ClientRepository {

    void create(Client client);

    List<Client> findAll();

    Client findById(Long id);

    int update(Client client);

    int delete(Client client);
}
