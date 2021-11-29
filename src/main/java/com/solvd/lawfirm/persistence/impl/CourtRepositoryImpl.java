package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Court;
import com.solvd.lawfirm.domain.exception.ProcessingException;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.CourtRepository;
import com.solvd.lawfirm.persistence.CourtTypeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourtRepositoryImpl implements CourtRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final CourtRepositoryImpl INSTANCE = new CourtRepositoryImpl();
    private static final CourtTypeRepository COURT_TYPE_REPOSITORY = CourtTypeRepositoryImpl.getInstance();

    private static final String CREATE_COURT_QUERY = "insert into Courts (court_type_id, name, address) values (?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select c.id as court_id, c.court_type_id as court_type_id, c.name as name, c.address  as address" +
            " from Courts c ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Courts set court_type_id = ?, name = ?, address = ? where id = ?";
    private static final String DELETE_QUERY = "delete from Courts where id = ?";

    private CourtRepositoryImpl() {
    }

    public static CourtRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Court court) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COURT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, court.getType().getId());
            preparedStatement.setString(2, court.getName());
            preparedStatement.setString(3, court.getAddress());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                court.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("'create'", "'CourtRepository'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Court> findAll() {
        List<Court> courts = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            courts = mapCourts(rs);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findAdd'", "'CourtRepository'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return courts;
    }

    @Override
    public Court findById(Long id) {
        Court court = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                court = mapCourt(rs);
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findById'", "'CourtRepository'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return court;
    }

    @Override
    public int update(Court court) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (court.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setLong(1, court.getType().getId());
                preparedStatement.setString(2, court.getName());
                preparedStatement.setString(3, court.getAddress());
                preparedStatement.setLong(4, court.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'update'", "'CourtRepository'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Court court) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (court.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, court.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'delete'", "'CourtRepository'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Court> mapCourts(ResultSet rs) throws SQLException {
        List<Court> courts = new ArrayList<>();
        while (rs.next()) {
            courts.add(mapCourt(rs));
        }
        return courts;
    }

    public static Court mapCourt(ResultSet rs) throws SQLException {
        Court court = new Court();
        court.setId(rs.getLong("court_id"));
        court.setType(COURT_TYPE_REPOSITORY.findById(rs.getLong("court_type_id")));
        court.setName(rs.getString("name"));
        court.setAddress(rs.getString("address"));
        return court;
    }
}
