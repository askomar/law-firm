package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.domain.ClientFolder;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ClientFolderRepository;
import com.solvd.lawfirm.persistence.impl.mapper.ClientFolderMapperImpl;
import com.solvd.lawfirm.service.ClientFolderService;
import com.solvd.lawfirm.service.ClientService;

import java.util.List;

public class ClientFolderServiceImpl implements ClientFolderService {

    private static final ClientFolderRepository CLIENT_FOLDER_REPOSITORY = ClientFolderMapperImpl.getInstance();
    private static final ClientService CLIENT_SERVICE = ClientServiceImpl.getInstance();

    private static ClientFolderService instance;

    private ClientFolderServiceImpl() {
    }

    public static ClientFolderService getInstance() {
        if (instance == null) {
            instance = new ClientFolderServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(ClientFolder clientFolder, Long clientId) throws ParameterIsEmpty, ResourceNotFoundException {
        if (!isValid(clientFolder)) {
            throw new ParameterIsEmpty("Client folder one or more parameters are not specified");
        }
        Client client = CLIENT_SERVICE.findById(clientId);
        clientFolder.setClient(client);
        CLIENT_FOLDER_REPOSITORY.create(clientFolder);
    }

    @Override
    public List<ClientFolder> findAll() throws ResourceNotFoundException {
        List<ClientFolder> clientFolders = CLIENT_FOLDER_REPOSITORY.findAll();
        if (clientFolders.size() <= 0) {
            throw new ResourceNotFoundException("Client folders was not found");
        }
        return clientFolders;
    }

    @Override
    public ClientFolder findById(Long id) throws ResourceNotFoundException {
        ClientFolder clientFolder = CLIENT_FOLDER_REPOSITORY.findById(id);
        if (clientFolder == null) {
            throw new ResourceNotFoundException("Client folder with id = " + id + " was not found");
        }
        return clientFolder;
    }

    @Override
    public int update(ClientFolder clientFolder) throws ParameterIsEmpty, ResourceNotFoundException {
        if (clientFolder.getId() == null) {
            throw new ParameterIsEmpty("Can't update client folder - there is no id");
        }
        if (!isValid(clientFolder)) {
            throw new ParameterIsEmpty("Client folder one or more parameters are not specified");
        }
        if (!clientFolder.getClient().equals(CLIENT_SERVICE.findById(clientFolder.getClient().getId()))) {
            CLIENT_SERVICE.update(clientFolder.getClient());
        }
        return CLIENT_FOLDER_REPOSITORY.update(clientFolder);
    }

    @Override
    public int delete(ClientFolder clientFolder) throws ParameterIsEmpty {
        if (clientFolder.getId() == null) {
            throw new ParameterIsEmpty("Can't delete client folder - there is no id");
        }
        return CLIENT_FOLDER_REPOSITORY.delete(clientFolder);
    }

    boolean isValid(ClientFolder clientFolder) {
        return clientFolder.getClient() != null && clientFolder.getStatus() != null;
    }
}
