package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.ServiceType;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.ServiceTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeRepositoryImpl implements ServiceTypeRepository {

    private static final Logger LOGGER = LogManager.getLogger(ServiceTypeRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final ServiceTypeRepositoryImpl INSTANCE = new ServiceTypeRepositoryImpl();

    private static final String CREATE_ORIENTATION_QUERY = "insert into Service_types (name) values (?)";
    private static final String FIND_ALL_QUERY = "select s.id as service_id, s.name as name " +
            " from Service_types s ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Service_types set name=?  where id = ?";
    private static final String DELETE_QUERY = "delete from Service_types where id = ?";

    private ServiceTypeRepositoryImpl() {
    }

    public static ServiceTypeRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(ServiceType serviceType) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORIENTATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, serviceType.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                serviceType.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create service type");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<ServiceType> findAll() {
        List<ServiceType> serviceTypes = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();
            serviceTypes = mapServiceTypes(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all service types");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return serviceTypes;
    }

    @Override
    public ServiceType findById(Long id) {
        ServiceType serviceType = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                serviceType = mapServiceType(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find service type by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return serviceType;
    }

    @Override
    public int update(ServiceType serviceType) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (serviceType.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, serviceType.getName());
                preparedStatement.setLong(2, serviceType.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update service type");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(ServiceType serviceType) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (serviceType.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, serviceType.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete service types");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<ServiceType> mapServiceTypes(ResultSet rs) throws SQLException {
        List<ServiceType> serviceTypes = new ArrayList<>();
        while (rs.next()) {
            serviceTypes.add(mapServiceType(rs));
        }
        return serviceTypes;
    }

    public static ServiceType mapServiceType(ResultSet rs) throws SQLException {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(rs.getLong("service_id"));
        serviceType.setName(rs.getString("name"));
        return serviceType;
    }
}
