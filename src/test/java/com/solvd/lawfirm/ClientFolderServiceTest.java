package com.solvd.lawfirm;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.domain.ClientFolder;
import com.solvd.lawfirm.domain.ClientFolderStatus;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.service.ClientFolderService;
import com.solvd.lawfirm.service.ClientService;
import com.solvd.lawfirm.service.impl.ClientFolderServiceImpl;
import com.solvd.lawfirm.service.impl.ClientServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ClientFolderServiceTest {

    private ClientService clientService;
    private ClientFolderService clientFolderService;

    @BeforeClass
    public void initServices() {
        clientService = ClientServiceImpl.getInstance();
        clientFolderService = ClientFolderServiceImpl.getInstance();
    }

    @Test(groups = "database")
    public void verifyClientFolderCreationTest() throws ResourceNotFoundException, ParameterIsEmpty {
        Client client = clientService.findAll().get(0);
        Assert.assertNotNull(client, "There should be at least one client in db");
        Assert.assertNotNull(client.getId(), "Client should have id when we get it from db");
        ClientFolder folder = new ClientFolder();
        folder.setStatus(ClientFolderStatus.ACTIVE);
        folder.setClient(client);
        clientFolderService.create(folder, client.getId());
        Assert.assertNotNull(folder.getId(), "ClientFolder object should have id after inserting it in db");
    }

    @Test(groups = "database", dependsOnMethods = "verifyClientFolderCreationTest")
    public void verifyClientFolderFindingAllTest() throws ResourceNotFoundException {
        List<ClientFolder> clientFolders = clientFolderService.findAll();
        Assert.assertNotNull(clientFolders, "ClientFolders shouldn't be null");
    }

    @Test(groups = "database", dependsOnMethods = "verifyClientFolderCreationTest")
    public void verifyClientFolderFindingByIdTest() throws ResourceNotFoundException {
        ClientFolder clientFolder = clientFolderService.findAll().get(0);
        Assert.assertNotNull(clientFolder, "There should be at least one ClientFolder in db");
        ClientFolder clientFolderById = clientFolderService.findById(clientFolder.getId());
        Assert.assertNotNull(clientFolderById, "There should be client folder with id=" + clientFolder.getId());
    }

    @Test(groups = "database", dependsOnMethods = "verifyClientFolderCreationTest")
    public void verifyClientFolderUpdatingTest() throws ResourceNotFoundException, ParameterIsEmpty {
        ClientFolder clientFolder = clientFolderService.findAll().get(0);
        clientFolder.setStatus(ClientFolderStatus.ARCHIVED);
        Integer updateStatus = clientFolderService.update(clientFolder);
        Assert.assertNotEquals(updateStatus, -1, "Status of ClientFolder update shouldn't be -1");
    }

    @Test(groups = "database", dependsOnMethods = "verifyClientFolderCreationTest")
    public void verifyClientFolderDeletingTest() throws ResourceNotFoundException, ParameterIsEmpty {
        ClientFolder folder = clientFolderService.findAll().get(0);
        Assert.assertNotNull(folder, "There should be id in ClientFolder after getting it from db");
        Integer deleteStatus = clientFolderService.delete(folder);
        Assert.assertNotEquals(deleteStatus, -1, "Status of ClientFolder deleting shouldn't be -1");
    }
}
