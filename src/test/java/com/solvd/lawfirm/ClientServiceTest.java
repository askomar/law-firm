package com.solvd.lawfirm;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.service.ClientService;
import com.solvd.lawfirm.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.List;

public class ClientServiceTest {

    private static final String LOG_MESSAGE = "Execute %s in %s";
    private static final String LOG_CLASS_NAME = ClientServiceTest.class.getSimpleName();
    private static final Logger LOGGER = LogManager.getLogger(LOG_CLASS_NAME);

    private static ClientService clientService;

    @BeforeSuite
    public void beforeSuite() {
        LOGGER.info(String.format(LOG_MESSAGE, "@BeforeSuite", LOG_CLASS_NAME));
    }

    @AfterSuite
    public void afterSuite() throws ResourceNotFoundException, ParameterIsEmpty {
        LOGGER.info("- " + String.format(LOG_MESSAGE + "\n", "@AfterSuite", LOG_CLASS_NAME));
        List<Client> clientList = clientService.findAll();
        for (Client client : clientList) {
            clientService.delete(client);
        }
    }

    @BeforeTest
    public void beforeTest() {
        LOGGER.info(String.format("- " + LOG_MESSAGE, "@BeforeTest", LOG_CLASS_NAME));
    }

    @AfterTest
    public void afterTest() {
        LOGGER.info(String.format("-- " + LOG_MESSAGE, "@AfterTest", LOG_CLASS_NAME));
    }

    @BeforeClass
    public static void initClientService() {
        LOGGER.info(String.format("-- " + LOG_MESSAGE, "@BeforeClass", LOG_CLASS_NAME));
        clientService = ClientServiceImpl.getInstance();
    }

    @AfterClass
    public void afterClass() {
        LOGGER.info(String.format("--- " + LOG_MESSAGE, "@AfterClass", LOG_CLASS_NAME));
    }

    @BeforeGroups
    public static void beforeGroups() {
        LOGGER.info(String.format("--- " + LOG_MESSAGE, "@BeforeGroups", LOG_CLASS_NAME));
    }

    @AfterGroups
    public void afterGroup() {
        LOGGER.info(String.format("---- " + LOG_MESSAGE, "@AferGroups", LOG_CLASS_NAME));
    }

    @BeforeMethod
    public void beforeMethod() {
        LOGGER.info(String.format("----- " + LOG_MESSAGE, "@BeforeMethod", LOG_CLASS_NAME));
    }

    @AfterMethod
    public void afterMethod() {
        LOGGER.info(String.format("----- " + LOG_MESSAGE, "@AfterMethod", LOG_CLASS_NAME));
    }

    @DataProvider(name = "users")
    public Object[][] users() {
        LOGGER.info(String.format("----- " + LOG_MESSAGE, "@DataProvider - users", LOG_CLASS_NAME));
        return new Object[][]{
                {"Kamarouski", "Andrei", "Sergeevich", LocalDate.of(1998, 4, 27), "8800-555-35-35"},
                {"Shagal", "Mark", "Zakharovich", LocalDate.of(1987, 6, 24), "8800-700-27-27"},
                {"Lazar", "Bogsha", null, LocalDate.of(1999, 4, 21), "8800-700-28-88"}
        };
    }

    @Test(dataProvider = "users", groups = "database")
    public void verifyClientsCreationTest(String surname, String name, String patronymic, LocalDate dob, String tel) throws ParameterIsEmpty {
        LOGGER.info(String.format("------ " + LOG_MESSAGE, "@Test - createClients", LOG_CLASS_NAME));
        Client client = new Client(surname, name, patronymic, dob, tel);
        clientService.create(client);
        Long clientId = client.getId();
        Assert.assertNotNull(clientId, "Client id can't be null after creation it in db");
    }

    @Test(expectedExceptions = {ParameterIsEmpty.class}, groups = "database")
    public void verifyClientCreationWithExceptionTest() throws ParameterIsEmpty {
        LOGGER.info(String.format("------ " + LOG_MESSAGE, "@Test - createClientWithException", LOG_CLASS_NAME));
        Client client = new Client();
        clientService.create(client);
    }

    @Test(groups = "database", dependsOnMethods = "verifyClientsCreationTest")
    public void vefifyAllClientsFindingTest() throws ResourceNotFoundException {
        LOGGER.info(String.format("------ " + LOG_MESSAGE, "@Test - findAllClientsTest", LOG_CLASS_NAME));
        List<Client> clients = clientService.findAll();
        Assert.assertNotNull(clients, "findAll method of clientService should return one or more clients");
    }

    @Test(groups = "database", dependsOnMethods = "verifyClientsCreationTest")
    public void verifyClientFindingByIdTest() throws ResourceNotFoundException {
        LOGGER.info(String.format("------ " + LOG_MESSAGE, "@Test - findClientById", LOG_CLASS_NAME));
        List<Client> clients = clientService.findAll();
        Client client = clientService.findById(clients.get(0).getId());
        Assert.assertNotNull(client, "There should be at least one client");
        Assert.assertNotNull(client.getId(), "Client should have id when we get it from db");
        Assert.assertNotNull(client.getSurname(), "Client should have surname when we get it from db");
        Assert.assertNotNull(client.getName(), "Client should have name when we get it from db");
        Assert.assertNotNull(client.getDob(), "Client should have date of birth when we get it from db");
        Assert.assertNotNull(client.getTelephone(), "Client should have telephone when we get it from db");
    }

    @Test(groups = {"database"}, dependsOnMethods = {"verifyClientsCreationTest"})
    public void verifyClientUpdatingTest() throws ResourceNotFoundException, ParameterIsEmpty {
        LOGGER.info(String.format("------ " + LOG_MESSAGE, "@Test - updateClient", LOG_CLASS_NAME));
        List<Client> clients = clientService.findAll();
        Client client = clientService.findById(clients.get(0).getId());
        client.setSurname("MODIFIED");
        client.setName("MODIFIED");
        client.setPatronymic("MODIFIED");
        client.setDob(LocalDate.of(2021, 1, 1));
        client.setTelephone("6666-666-66-66");
        clientService.update(client);
        Client modifiedClient = clientService.findById(client.getId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(modifiedClient.getSurname(), client.getSurname(), "Client surname should be modified");
        softAssert.assertEquals(modifiedClient.getName(), client.getName(), "Client name should be modified");
        softAssert.assertEquals(modifiedClient.getPatronymic(), client.getPatronymic(), "Client patronymic should be modified");
        softAssert.assertEquals(modifiedClient.getDob(), client.getDob(), "Client dob should be modified");
        softAssert.assertEquals(modifiedClient.getTelephone(), client.getTelephone(), "Client telephone should be modified");
        softAssert.assertAll();
    }

    @Test(groups = {"database"}, dependsOnMethods = "verifyClientsCreationTest")
    public void verifyClientDeletingTest() throws ResourceNotFoundException, ParameterIsEmpty {
        List<Client> clients = clientService.findAll();
        Assert.assertNotNull(clients, "For deleting client there should be at least one client in db");
        Integer deleteResult = clientService.delete(clients.get(0));
        Assert.assertNotEquals(deleteResult, -1, "delete method shouldn't return -1 as a result");
    }
}
