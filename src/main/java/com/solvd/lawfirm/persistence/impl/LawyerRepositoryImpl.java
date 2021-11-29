package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Lawyer;
import com.solvd.lawfirm.domain.exception.ProcessingException;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.LawOfficeRepository;
import com.solvd.lawfirm.persistence.LawyerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LawyerRepositoryImpl implements LawyerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final LawyerRepositoryImpl INSTANCE = new LawyerRepositoryImpl();
    private static final LawOfficeRepository LAW_OFFICE_REPOSITORY = LawOfficeRepositoryImpl.getInstance();

    private static final String CREATE_LAWYER_QUERY = "insert into Lawyers (law_office_id, surname, `name`, patronymic, dob, experience_since) values  (?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select l.id as lawyer_id, l.law_office_id as office_id, l.surname as surname, l.`name` as name, l.patronymic as patronymic, l.dob as dob, l.experience_since as experience_since " +
            " from Lawyers l ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Lawyers set law_office_id=?, surname=?, `name`=?, patronymic=?, dob=?, experience_since=?  where id = ?";
    private static final String DELETE_QUERY = "delete from Lawyers where id = ?";

    private LawyerRepositoryImpl() {
    }

    public static LawyerRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Lawyer lawyer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LAWYER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, lawyer.getLawOffice().getId());
            preparedStatement.setString(2, lawyer.getSurname());
            preparedStatement.setString(3, lawyer.getName());
            preparedStatement.setString(4, lawyer.getPatronymic());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(lawyer.getDob().atStartOfDay()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(lawyer.getExperienceSince().atStartOfDay()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                lawyer.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("'create'", "'Lawyer'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Lawyer> findAll() {
        List<Lawyer> lawyers = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();
            lawyers = mapLawyers(rs);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findAll'", "'Lawyer'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lawyers;
    }

    @Override
    public Lawyer findById(Long id) {
        Lawyer lawyer = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lawyer = mapLawyer(rs);
            }
        } catch (SQLException e) {
            throw new ProcessingException("'findById'", "'Lawyer'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lawyer;
    }

    @Override
    public int update(Lawyer lawyer) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (lawyer.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setLong(1, lawyer.getLawOffice().getId());
                preparedStatement.setString(2, lawyer.getSurname());
                preparedStatement.setString(3, lawyer.getName());
                preparedStatement.setString(4, lawyer.getPatronymic());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(lawyer.getDob().atStartOfDay()));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(lawyer.getExperienceSince().atStartOfDay()));
                preparedStatement.setLong(7, lawyer.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'update'", "'Lawyer'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Lawyer lawyer) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (lawyer.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, lawyer.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'delete'", "'Lawyer'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Lawyer> mapLawyers(ResultSet rs) throws SQLException {
        List<Lawyer> lawyers = new ArrayList<>();
        while (rs.next()) {
            lawyers.add(mapLawyer(rs));
        }
        return lawyers;
    }

    public static Lawyer mapLawyer(ResultSet rs) throws SQLException {
        Lawyer lawyer = new Lawyer();
        lawyer.setId(rs.getLong("lawyer_id"));
        lawyer.setLawOffice(LAW_OFFICE_REPOSITORY.findById(rs.getLong("office_id")));
        lawyer.setSurname(rs.getString("surname"));
        lawyer.setName(rs.getString("name"));
        lawyer.setPatronymic(rs.getString("patronymic"));
        lawyer.setDob(rs.getTimestamp("dob").toLocalDateTime().toLocalDate());
        lawyer.setExperienceSince(rs.getTimestamp("experience_since").toLocalDateTime().toLocalDate());
        return lawyer;
    }
}
