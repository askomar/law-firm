package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.persistence.ClientRepository;
import com.solvd.lawfirm.persistence.impl.ClientRepositoryImpl;
import com.solvd.lawfirm.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static final ClientRepository CLIENT_REPOSITORY = ClientRepositoryImpl.getInstance();

    @Override
    public void create(Client client) {
        CLIENT_REPOSITORY.create(client);
    }

    @Override
    public List<Client> findAll() {
        return CLIENT_REPOSITORY.findAll();
    }

    @Override
    public Client findById(Long id) {
        return CLIENT_REPOSITORY.findById(id);
    }

    @Override
    public int update(Client client) {
        return CLIENT_REPOSITORY.update(client);
    }

    @Override
    public int delete(Client client) {
        return CLIENT_REPOSITORY.delete(client);
    }
}
