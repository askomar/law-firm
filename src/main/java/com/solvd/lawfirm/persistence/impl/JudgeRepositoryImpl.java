package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Judge;
import com.solvd.lawfirm.domain.exception.ProcessingException;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.ConnectionPool;
import com.solvd.lawfirm.persistence.JudgeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JudgeRepositoryImpl implements JudgeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final JudgeRepositoryImpl INSTANCE = new JudgeRepositoryImpl();

    private static final String CREATE_JUDGE_QUERY = "insert into Judges (surname, name, patronymic, dob, experience_since) values (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select j.id as judge_id, j.surname as surname, j.name as name, j.patronymic as patronymic, j.dob as dob, j.experience_since as experience_since " +
            " from Judges j ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Judges set surname=?, name=?, patronymic=?, dob=?, experience_since=? where id = ?";
    private static final String DELETE_QUERY = "delete from Judges where id = ?";

    private JudgeRepositoryImpl() {
    }

    public static JudgeRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Judge judge) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_JUDGE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, judge.getSurname());
            preparedStatement.setString(2, judge.getName());
            preparedStatement.setString(3, judge.getPatronymic());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(judge.getDob().atStartOfDay()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(judge.getExperienceSince().atStartOfDay()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                judge.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("'create'", "'Judge'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Judge> findAll() {
        List<Judge> judges = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            judges = mapJudges(rs);
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findAll'", "'Judge'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return judges;
    }

    @Override
    public Judge findById(Long id) {
        Judge judge = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                judge = mapJudge(rs);
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("'findById'", "'Judge'", e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return judge;
    }

    @Override
    public int update(Judge judge) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (judge.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, judge.getSurname());
                preparedStatement.setString(2, judge.getName());
                preparedStatement.setString(3, judge.getPatronymic());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(judge.getDob().atStartOfDay()));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(judge.getExperienceSince().atStartOfDay()));
                preparedStatement.setLong(6, judge.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'update'", "'Judge'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Judge judge) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (judge.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, judge.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ProcessingException("'delete'", "'Judge'", e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Judge> mapJudges(ResultSet rs) throws SQLException {
        List<Judge> judges = new ArrayList<>();
        while (rs.next()) {
            judges.add(mapJudge(rs));
        }
        return judges;
    }

    public static Judge mapJudge(ResultSet rs) throws SQLException {
        Judge judge = new Judge();
        judge.setId(rs.getLong("judge_id"));
        judge.setSurname(rs.getString("surname"));
        judge.setName(rs.getString("name"));
        judge.setPatronymic(rs.getString("patronymic"));
        judge.setDob(rs.getTimestamp("dob").toLocalDateTime().toLocalDate());
        judge.setExperienceSince(rs.getTimestamp("experience_since").toLocalDateTime().toLocalDate());
        return judge;
    }
}
