package com.solvd.lawfirm.persistence;

import com.solvd.lawfirm.domain.ClientFolder;

import java.util.List;

public interface ClientFolderRepository {

    void create(ClientFolder clientFolder);

    List<ClientFolder> findAll();

    ClientFolder findById(Long id);

    int update(ClientFolder clientFolder);

    int delete(ClientFolder clientFolder);
}
