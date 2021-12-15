package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.LawyerActivitySphere;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.LawyerActivitySphereRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LawyerActivitySphereRepositoryImpl implements LawyerActivitySphereRepository {

    private static final Logger LOGGER = LogManager.getLogger(LawyerActivitySphereRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final LawyerActivitySphereRepositoryImpl INSTANCE = new LawyerActivitySphereRepositoryImpl();

    private static final String CREATE_LAWYER_ACTIVITY_SPHERE_QUERY = "insert into Lawyer_activity_spheres (name) values (?)";
    private static final String FIND_ALL_QUERY = "select l.id as lawyer_activity_sphere_id, l.name as name" +
            " from Lawyer_activity_spheres l ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Lawyer_activity_spheres set name=?  where id = ?";
    private static final String DELETE_QUERY = "delete from Lawyer_activity_spheres where id = ?";

    private LawyerActivitySphereRepositoryImpl() {
    }

    public static LawyerActivitySphereRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(LawyerActivitySphere lawyerActivitySphere) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LAWYER_ACTIVITY_SPHERE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lawyerActivitySphere.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                lawyerActivitySphere.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create lawyer activity sphere");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<LawyerActivitySphere> findAll() {
        List<LawyerActivitySphere> lawyerActivitySpheres = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();
            lawyerActivitySpheres = mapLawyerActivitySpheres(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all lawyer activity spheres");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lawyerActivitySpheres;
    }

    @Override
    public LawyerActivitySphere findById(Long id) {
        LawyerActivitySphere lawyerActivitySphere = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lawyerActivitySphere = mapLawyerActivitySphere(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find lawyer activity sphere by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lawyerActivitySphere;
    }

    @Override
    public int update(LawyerActivitySphere lawyerActivitySphere) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (lawyerActivitySphere.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, lawyerActivitySphere.getName());
                preparedStatement.setLong(2, lawyerActivitySphere.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update lawyer activity sphere");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(LawyerActivitySphere lawyerActivitySphere) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (lawyerActivitySphere.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, lawyerActivitySphere.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete lawyer activity sphere");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<LawyerActivitySphere> mapLawyerActivitySpheres(ResultSet rs) throws SQLException {
        List<LawyerActivitySphere> lawyerActivitySpheres = new ArrayList<>();
        while (rs.next()) {
            lawyerActivitySpheres.add(mapLawyerActivitySphere(rs));
        }
        return lawyerActivitySpheres;
    }

    public static LawyerActivitySphere mapLawyerActivitySphere(ResultSet rs) throws SQLException {
        LawyerActivitySphere lawyerActivitySphere = new LawyerActivitySphere();
        lawyerActivitySphere.setId(rs.getLong("lawyer_activity_sphere_id"));
        lawyerActivitySphere.setName(rs.getString("name"));
        return lawyerActivitySphere;
    }
}
