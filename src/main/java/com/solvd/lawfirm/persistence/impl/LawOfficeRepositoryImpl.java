package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.LawOffice;
import com.solvd.lawfirm.domain.exception.ProcessingException;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.LawOfficeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LawOfficeRepositoryImpl implements LawOfficeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final LawOfficeRepositoryImpl INSTANCE = new LawOfficeRepositoryImpl();

    private static final String CREATE_LAW_OFFICE_QUERY = "insert into Law_offices (name, address) values (?, ?)";
    private static final String FIND_ALL_QUERY = "select id, name, address from Law_offices";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Law_offices set name=?, address=? where id = ?";
    private static final String DELETE_QUERY = "delete from Law_offices where id = ?";

    private LawOfficeRepositoryImpl() {
    }

    public static LawOfficeRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(LawOffice lawOffice) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LAW_OFFICE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lawOffice.getName());
            preparedStatement.setString(2, lawOffice.getAddress());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                lawOffice.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("'create'", "'LawOffice'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<LawOffice> findAll() {
        List<LawOffice> lawOffices = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();
            lawOffices = mapLawOffices(rs);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findAll'", "'LawOffice'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lawOffices;
    }

    @Override
    public LawOffice findById(Long id) {
        LawOffice lawOffice = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lawOffice = mapLawOffice(rs);
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findById'", "'LawOffice'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lawOffice;
    }

    @Override
    public int update(LawOffice lawOffice) {
        int row = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (lawOffice.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, lawOffice.getName());
                preparedStatement.setString(2, lawOffice.getAddress());
                preparedStatement.setLong(3, lawOffice.getId());
                row = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'update'", "'LawOffice'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return row;
    }

    @Override
    public int delete(LawOffice lawOffice) {
        int row = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (lawOffice.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, lawOffice.getId());
                row = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'delete'", "'LawOffice'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return row;
    }

    public List<LawOffice> mapLawOffices(ResultSet rs) throws SQLException {
        List<LawOffice> lawOffices = new ArrayList<>();
        while (rs.next()) {
            lawOffices.add(mapLawOffice(rs));
        }
        return lawOffices;
    }

    private LawOffice mapLawOffice(ResultSet rs) throws SQLException {
        LawOffice lawOffice = new LawOffice();
        lawOffice.setId(rs.getLong("id"));
        lawOffice.setName(rs.getString("name"));
        lawOffice.setAddress(rs.getString("address"));
        return lawOffice;
    }
}
