package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.PaperWorkType;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.PaperworkTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperworkTypeRepositoryImpl implements PaperworkTypeRepository {

    private static final Logger LOGGER = LogManager.getLogger(PaperworkTypeRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PaperworkTypeRepositoryImpl INSTANCE = new PaperworkTypeRepositoryImpl();
    private static final String CREATE_PAPERWORK_TYPE_QUERY = "insert into Paperwork_types (name) values (?)";
    private static final String FIND_ALL_QUERY = "select t.id as paperwork_type_id, t.name as name " +
            " from Paperwork_types t ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Paperwork_types set name=?  where id = ?";
    private static final String DELETE_QUERY = "delete from Paperwork_types where id = ?";

    private PaperworkTypeRepositoryImpl() {
    }

    public static PaperworkTypeRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(PaperWorkType paperWorkType) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PAPERWORK_TYPE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, paperWorkType.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                paperWorkType.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create paperwork type");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<PaperWorkType> findAll() {
        List<PaperWorkType> paperWorkTypes = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            paperWorkTypes = mapPaperWorkTypes(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all paperwork types");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return paperWorkTypes;
    }

    @Override
    public PaperWorkType findById(Long id) {
        PaperWorkType paperWorkType = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                paperWorkType = mapPaperWorkType(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find paperwork type by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return paperWorkType;
    }

    @Override
    public int update(PaperWorkType paperWorkType) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (paperWorkType.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, paperWorkType.getName());
                preparedStatement.setLong(2, paperWorkType.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update paperwork type");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(PaperWorkType paperWorkType) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (paperWorkType.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, paperWorkType.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete paperwork type");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<PaperWorkType> mapPaperWorkTypes(ResultSet rs) throws SQLException {
        List<PaperWorkType> paperWorkTypes = new ArrayList<>();
        while (rs.next()) {
            paperWorkTypes.add(mapPaperWorkType(rs));
        }
        return paperWorkTypes;
    }

    public static PaperWorkType mapPaperWorkType(ResultSet rs) throws SQLException {
        PaperWorkType paperWorkType = new PaperWorkType();
        paperWorkType.setId(rs.getLong("paperwork_type_id"));
        paperWorkType.setName(rs.getString("name"));
        return paperWorkType;
    }
}
