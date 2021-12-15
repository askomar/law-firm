package com.solvd.lawfirm.service;

import com.solvd.lawfirm.domain.ClientFolder;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;

import java.util.List;

public interface ClientFolderService {

    void create(ClientFolder clientFolder, Long clientId) throws ParameterIsEmpty, ResourceNotFoundException;

    List<ClientFolder> findAll() throws ResourceNotFoundException;

    ClientFolder findById(Long id) throws ResourceNotFoundException;

    int update(ClientFolder orientation) throws ParameterIsEmpty, ResourceNotFoundException;

    int delete(ClientFolder clientFolder) throws ParameterIsEmpty;
}
