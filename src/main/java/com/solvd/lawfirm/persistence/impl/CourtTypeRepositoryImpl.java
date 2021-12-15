package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.CourtType;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.CourtTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourtTypeRepositoryImpl implements CourtTypeRepository {

    private static final Logger LOGGER = LogManager.getLogger(CourtTypeRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final CourtTypeRepositoryImpl INSTANCE = new CourtTypeRepositoryImpl();

    private static final String CREATE_COURT_TYPE_QUERY = "insert into Court_types (name) values (?)";
    private static final String FIND_ALL_QUERY = "select t.id as court_type_id, t.name as name " +
            " from Court_types t ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Court_types set name= ? where id = ?";
    private static final String DELETE_QUERY = "delete from Court_types where id = ?";

    private CourtTypeRepositoryImpl() {
    }

    public static CourtTypeRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(CourtType courtType) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COURT_TYPE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, courtType.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                courtType.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create court type");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<CourtType> findAll() {
        List<CourtType> courtTypes = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            courtTypes = mapCourtTypes(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all court types");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return courtTypes;
    }

    @Override
    public CourtType findById(Long id) {
        CourtType courtType = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                courtType = mapCourtType(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find court type by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return courtType;
    }

    @Override
    public int update(CourtType courtType) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (courtType.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, courtType.getName());
                preparedStatement.setLong(2, courtType.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update court type");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(CourtType courtType) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (courtType.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, courtType.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete court type");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<CourtType> mapCourtTypes(ResultSet rs) throws SQLException {
        List<CourtType> courtTypes = new ArrayList<>();
        while (rs.next()) {
            courtTypes.add(mapCourtType(rs));
        }
        return courtTypes;
    }

    public static CourtType mapCourtType(ResultSet rs) throws SQLException {
        CourtType courtType = new CourtType();
        courtType.setId(rs.getLong("court_type_id"));
        courtType.setName(rs.getString("name"));
        return courtType;
    }
}
