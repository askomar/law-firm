package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Orientation;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.LawyerActivitySphereRepository;
import com.solvd.lawfirm.persistence.LawyerRepository;
import com.solvd.lawfirm.persistence.OrientationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrientationRepositoryImpl implements OrientationRepository {

    private static final Logger LOGGER = LogManager.getLogger(OrientationRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final OrientationRepositoryImpl INSTANCE = new OrientationRepositoryImpl();
    private static final LawyerRepository LAWYER_REPOSITORY = LawyerRepositoryImpl.getInstance();
    private static final LawyerActivitySphereRepository LAWYER_ACTIVITY_SPHERE_REPOSITORY = LawyerActivitySphereRepositoryImpl.getInstance();

    private static final String CREATE_ORIENTATION_QUERY = "insert into Orientations (lawyer_id, activity_sphere_id) values  (?, ?)";
    private static final String FIND_ALL_QUERY = "select o.id as orientation_id, o.lawyer_id as lawyer_id, o.activity_sphere_id as activity_sphere_id" +
            " from Orientations o ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Orientations set lawyer_id=?, activity_sphere_id=?  where id = ?";
    private static final String DELETE_QUERY = "delete from Orientations where id = ?";

    private OrientationRepositoryImpl() {
    }

    public static OrientationRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Orientation orientation) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORIENTATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, orientation.getLawyer().getId());
            preparedStatement.setLong(2, orientation.getLawyerActivitySphere().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                orientation.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create orientation");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Orientation> findAll() {
        List<Orientation> orientations = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();
            orientations = mapOrientations(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all orientations");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return orientations;
    }

    @Override
    public Orientation findById(Long id) {
        Orientation orientation = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orientation = mapOrientation(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find orientation by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return orientation;
    }

    @Override
    public int update(Orientation orientation) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (orientation.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setLong(1, orientation.getLawyer().getId());
                preparedStatement.setLong(2, orientation.getLawyerActivitySphere().getId());
                preparedStatement.setLong(3, orientation.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update orientation");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Orientation orientation) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (orientation.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, orientation.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete orientation");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Orientation> mapOrientations(ResultSet rs) throws SQLException {
        List<Orientation> orientations = new ArrayList<>();
        while (rs.next()) {
            orientations.add(mapOrientation(rs));
        }
        return orientations;
    }

    public static Orientation mapOrientation(ResultSet rs) throws SQLException {
        Orientation orientation = new Orientation();
        orientation.setId(rs.getLong("orientation_id"));
        orientation.setLawyer(LAWYER_REPOSITORY.findById(rs.getLong("lawyer_id")));
        orientation.setLawyerActivitySphere(LAWYER_ACTIVITY_SPHERE_REPOSITORY.findById(rs.getLong("activity_sphere_id")));
        return orientation;
    }
}
