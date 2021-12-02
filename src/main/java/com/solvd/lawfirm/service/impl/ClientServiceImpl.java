package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ClientRepository;
import com.solvd.lawfirm.persistence.impl.mapper.ClientMapperImpl;
import com.solvd.lawfirm.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static final ClientRepository CLIENT_REPOSITORY = ClientMapperImpl.getInstance();

    private static ClientService instance;

    private ClientServiceImpl() {
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Client client) throws ParameterIsEmpty {
        if (!isValid(client)) {
            throw new ParameterIsEmpty("Client's one or more parameters are not specified");
        }
        CLIENT_REPOSITORY.create(client);
    }

    @Override
    public List<Client> findAll() throws ResourceNotFoundException {
        List<Client> clients = CLIENT_REPOSITORY.findAll();
        if (clients.size() <= 0) {
            throw new ResourceNotFoundException("Clients was not found");
        }
        return clients;
    }

    @Override
    public Client findById(Long id) throws ResourceNotFoundException {
        Client client = CLIENT_REPOSITORY.findById(id);
        if (client == null) {
            throw new ResourceNotFoundException("Client with id = " + id + " was not found");
        }
        return client;
    }

    @Override
    public int update(Client client) throws ParameterIsEmpty {
        if (client.getId() == null) {
            throw new ParameterIsEmpty("Can't update client - there is no id");
        }
        if (!isValid(client)) {
            throw new ParameterIsEmpty("Client's one or more parameters are not specified");
        }
        return CLIENT_REPOSITORY.update(client);
    }

    @Override
    public int delete(Client client) throws ParameterIsEmpty {
        if (client.getId() == null) {
            throw new ParameterIsEmpty("Can't delete client - there is no id");
        }
        return CLIENT_REPOSITORY.delete(client);
    }

    private boolean isValid(Client client) {
        return client.getSurname() != null && client.getName() != null && client.getDob() != null && client.getTelephone() != null;
    }
}
