package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Service;
import com.solvd.lawfirm.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepositoryImpl implements ServiceRepository {

    private static final Logger LOGGER = LogManager.getLogger(ServiceRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final ServiceRepositoryImpl INSTANCE = new ServiceRepositoryImpl();
    private static final ServiceTypeRepository SERVICE_TYPE_REPOSITORY = ServiceTypeRepositoryImpl.getInstance();
    private static final LawyerRepository LAWYER_REPOSITORY = LawyerRepositoryImpl.getInstance();
    private static final PaperworkRepository PAPERWORK_REPOSITORY = PaperworkRepositoryImpl.getInstance();

    private static final String CREATE_SERVICE_QUERY = "insert into Services (type_id, lawyer_id, paperwork_id, cost) values (?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select s.id as service_id,   s.type_id as type_id, s.lawyer_id as lawyer_id, s.paperwork_id as paperwork_id, s.cost as cost" +
            " from Services s ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Services set type_id=?, lawyer_id=?, paperwork_id=?, cost=?  where id = ?";
    private static final String DELETE_QUERY = "delete from Services where id = ?";

    private ServiceRepositoryImpl() {
    }

    public static ServiceRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Service service) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SERVICE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, service.getType().getId());
            preparedStatement.setLong(2, service.getLawyer().getId());
            preparedStatement.setLong(3, service.getPaperWork().getId());
            preparedStatement.setBigDecimal(4, service.getCost());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                service.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create service");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            services = mapServices(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all services");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return services;
    }

    @Override
    public Service findById(Long id) {
        Service service = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                service = mapService(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find service by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return service;
    }

    @Override
    public int update(Service service) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (service.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setLong(1, service.getType().getId());
                preparedStatement.setLong(2, service.getLawyer().getId());
                preparedStatement.setLong(3, service.getPaperWork().getId());
                preparedStatement.setBigDecimal(4, service.getCost());
                preparedStatement.setLong(5, service.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update service");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Service service) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (service.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, service.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete service");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Service> mapServices(ResultSet rs) throws SQLException {
        List<Service> services = new ArrayList<>();
        while (rs.next()) {
            services.add(mapService(rs));
        }
        return services;
    }

    public static Service mapService(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getLong("service_id"));
        service.setType(SERVICE_TYPE_REPOSITORY.findById(rs.getLong("type_id")));
        service.setLawyer(LAWYER_REPOSITORY.findById(rs.getLong("lawyer_id")));
        service.setPaperWork(PAPERWORK_REPOSITORY.findById(rs.getLong("paperwork_id")));
        service.setCost(rs.getBigDecimal("cost"));
        return service;
    }
}
