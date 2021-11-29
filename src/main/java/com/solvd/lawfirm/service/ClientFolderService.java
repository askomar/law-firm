package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.ClientFolder;

import java.util.List;

public interface ClientFolderService {

    void create(ClientFolder clientFolder);

    List<ClientFolder> findAll();

    ClientFolder findById(Long id);

    int update(ClientFolder orientation);

    int delete(ClientFolder clientFolder);
}
