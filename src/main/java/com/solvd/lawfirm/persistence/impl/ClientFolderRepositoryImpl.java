package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.ClientFolder;
import com.solvd.lawfirm.domain.ClientFolderStatus;
import com.solvd.lawfirm.persistence.ClientFolderRepository;
import com.solvd.lawfirm.persistence.ClientRepository;
import com.solvd.lawfirm.persistence.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClientFolderRepositoryImpl implements ClientFolderRepository {

    private static final Logger LOGGER = LogManager.getLogger(ClientFolderRepositoryImpl.class);

    private static final ClientFolderRepositoryImpl INSTANCE = new ClientFolderRepositoryImpl();
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final ClientRepository CLIENT_REPOSITORY = ClientRepositoryImpl.getInstance();

    private static final String CREATE_QUERY = "insert into Client_folders (client_id, `status`) values (?, ?)";
    private static final String FIND_ALL_QUERY = "select f.id as folder_id, f.client_id as client_id, f.status as status " +
            " from Client_folders f ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Client_folders set client_id= ?, `status` = ? where id = ?";
    private static final String DELETE_QUERY = "delete from Client_folders where id = ?";

    private ClientFolderRepositoryImpl() {
    }

    public static ClientFolderRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(ClientFolder clientFolder) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, clientFolder.getClient().getId());
            preparedStatement.setString(2, clientFolder.getStatus().name());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                clientFolder.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create client folder");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<ClientFolder> findAll() {
        List<ClientFolder> clientFolders = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            clientFolders = mapClientFolders(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all client folders");

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clientFolders;
    }

    @Override
    public ClientFolder findById(Long id) {
        ClientFolder clientFolder = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clientFolder = mapClientFolder(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find client folder by id");

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clientFolder;
    }

    @Override
    public int update(ClientFolder clientFolder) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (clientFolder.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, clientFolder.getStatus().name().toUpperCase(Locale.ROOT));
                preparedStatement.setLong(2, clientFolder.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update client folder");

            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(ClientFolder clientFolder) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (clientFolder.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, clientFolder.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete client folder");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<ClientFolder> mapClientFolders(ResultSet rs) throws SQLException {
        List<ClientFolder> clientFolders = new ArrayList<>();
        while (rs.next()) {
            clientFolders.add(mapClientFolder(rs));
        }
        return clientFolders;
    }

    public static ClientFolder mapClientFolder(ResultSet rs) throws SQLException {
        ClientFolder clientFolder = new ClientFolder();
        clientFolder.setId(rs.getLong("folder_id"));
        clientFolder.setClient(CLIENT_REPOSITORY.findById(rs.getLong("client_id")));
        clientFolder.setStatus(ClientFolderStatus.valueOf(rs.getString("status").toUpperCase(Locale.ROOT)));
        return clientFolder;
    }
}
