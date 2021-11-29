package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.domain.exception.ProcessingException;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ClientRepository;
import com.solvd.lawfirm.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {

    private static final ClientRepositoryImpl INSTANCE = new ClientRepositoryImpl();
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_CLIENT_QUERY = "insert into Clients (surname, `name`, patronymic, dob, telephone) values (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select c.id as client_id, c.surname as surname, c.`name` as name, c.patronymic as patronymic, c.dob as dob, c.telephone as telephone" +
            " from Clients c ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Clients set surname = ?, `name` = ?, patronymic = ?, dob = ?, telephone = ? where id = ?";
    private static final String DELETE_QUERY = "delete from Clients where id = ?";

    private ClientRepositoryImpl() {
    }

    public static ClientRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Client client) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getSurname());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getPatronymic());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getDob().atStartOfDay()));
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                client.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("'create'", "'ClientRepository'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();
            clients = mapClients(rs);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findAll'", "'ClientRepository'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return clients;
    }

    @Override
    public Client findById(Long id) {
        Client client = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                client = mapClient(rs);
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findById'", "'ClientRepository'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return client;
    }

    @Override
    public int update(Client client) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (client.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, client.getSurname());
                preparedStatement.setString(2, client.getName());
                preparedStatement.setString(3, client.getPatronymic());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getDob().atStartOfDay()));
                preparedStatement.setString(5, client.getTelephone());
                preparedStatement.setLong(6, client.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'update'", "'ClientRepository'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Client client) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (client.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, client.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'delete'", "'ClientRepository'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Client> mapClients(ResultSet rs) throws SQLException {
        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            clients.add(mapClient(rs));
        }
        return clients;
    }

    public static Client mapClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("client_id"));
        client.setSurname(rs.getString("surname"));
        client.setName(rs.getString("name"));
        client.setPatronymic(rs.getString("patronymic"));
        client.setDob(rs.getTimestamp("dob").toLocalDateTime().toLocalDate());
        client.setTelephone(rs.getString("telephone"));
        return client;
    }
}
