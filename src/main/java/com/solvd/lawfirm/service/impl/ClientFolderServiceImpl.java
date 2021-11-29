package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.ClientFolder;
import com.solvd.lawfirm.persistence.ClientFolderRepository;
import com.solvd.lawfirm.persistence.impl.ClientFolderRepositoryImpl;
import com.solvd.lawfirm.service.ClientFolderService;
import com.solvd.lawfirm.service.ClientService;

import java.util.List;

public class ClientFolderServiceImpl implements ClientFolderService {
    
    private static final ClientFolderRepository CLIENT_FOLDER_REPOSITORY = ClientFolderRepositoryImpl.getInstance();
    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl();

    @Override
    public void create(ClientFolder clientFolder) {
        if (clientFolder.getClient() != null && (clientFolder.getClient().getId()) == null) {
            CLIENT_SERVICE.create(clientFolder.getClient());
        }

        CLIENT_FOLDER_REPOSITORY.create(clientFolder);
    }

    @Override
    public List<ClientFolder> findAll() {
        return CLIENT_FOLDER_REPOSITORY.findAll();
    }

    @Override
    public ClientFolder findById(Long id) {
        return CLIENT_FOLDER_REPOSITORY.findById(id);
    }

    @Override
    public int update(ClientFolder orientation) {
        return CLIENT_FOLDER_REPOSITORY.update(orientation);
    }

    @Override
    public int delete(ClientFolder clientFolder) {
        return CLIENT_FOLDER_REPOSITORY.delete(clientFolder);
    }
}
