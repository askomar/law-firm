package com.solvd.lawfirm.persistence.impl;

import com.solvd.lawfirm.domain.Paperwork;
import com.solvd.lawfirm.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperworkRepositoryImpl implements PaperworkRepository {

    private static final Logger LOGGER = LogManager.getLogger(PaperworkRepositoryImpl.class);

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PaperworkRepositoryImpl INSTANCE = new PaperworkRepositoryImpl();
    private static final PaperworkTypeRepository PAPERWORK_TYPE_REPOSITORY = PaperworkTypeRepositoryImpl.getInstance();
    private static final ClientFolderRepository CLIENT_FOLDER_REPOSITORY = ClientFolderRepositoryImpl.getInstance();
    private static final CourtRepository COURT_REPOSITORY = CourtRepositoryImpl.getInstance();
    private static final JudgeRepository JUDGE_REPOSITORY = JudgeRepositoryImpl.getInstance();

    private static final String CREATE_PAPERWORK_QUERY = "insert into Paperworks (type_id, client_folder_id, court_id, judge_id, title, url) values  (?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "select p.id as paperwork_id,  p.type_id as type_id, p.client_folder_id as client_folder_id, p.court_id as court_id, p.judge_id as judge_id, p.title as title, p.url as url" +
            " from Paperworks p ";
    private static final String FIND_BY_ID = FIND_ALL_QUERY + " where id = ?";
    private static final String UPDATE_QUERY = "update Paperworks set type_id=?, client_folder_id=?, court_id=?, judge_id=?, title=?, url=?   where id = ?";
    private static final String DELETE_QUERY = "delete from Paperworks where id = ?";

    private PaperworkRepositoryImpl() {
    }

    public static PaperworkRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Paperwork paperwork) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PAPERWORK_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, paperwork.getType().getId());
            preparedStatement.setLong(2, paperwork.getFolder().getId());
            preparedStatement.setLong(3, paperwork.getCourt().getId());
            preparedStatement.setLong(4, paperwork.getJudge().getId());
            preparedStatement.setString(5, paperwork.getTitle());
            preparedStatement.setString(6, paperwork.getUrl());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                paperwork.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to create paperwork");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Paperwork> findAll() {
        List<Paperwork> paperworks = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();
            paperworks = mapPaperworks(rs);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find all paperworks");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return paperworks;
    }

    @Override
    public Paperwork findById(Long id) {
        Paperwork paperwork = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                paperwork = mapPaperwork(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception when try to find paperwork by id");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return paperwork;
    }

    @Override
    public int update(Paperwork paperwork) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (paperwork.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setLong(1, paperwork.getType().getId());
                preparedStatement.setLong(2, paperwork.getFolder().getId());
                preparedStatement.setLong(3, paperwork.getCourt().getId());
                preparedStatement.setLong(4, paperwork.getJudge().getId());
                preparedStatement.setString(5, paperwork.getTitle());
                preparedStatement.setString(6, paperwork.getUrl());
                preparedStatement.setLong(7, paperwork.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to update paperwork");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    @Override
    public int delete(Paperwork paperwork) {
        int rows = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        if (paperwork.getId() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setLong(1, paperwork.getId());
                rows = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("SQL exception when try to delete paperwork");
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
        }
        return rows;
    }

    public static List<Paperwork> mapPaperworks(ResultSet rs) throws SQLException {
        List<Paperwork> paperworks = new ArrayList<>();
        while (rs.next()) {
            paperworks.add(mapPaperwork(rs));
        }
        return paperworks;
    }

    public static Paperwork mapPaperwork(ResultSet rs) throws SQLException {
        Paperwork paperwork = new Paperwork();
        paperwork.setId(rs.getLong("paperwork_id"));
        paperwork.setType(PAPERWORK_TYPE_REPOSITORY.findById(rs.getLong("type_id")));
        paperwork.setFolder(CLIENT_FOLDER_REPOSITORY.findById(rs.getLong("client_folder_id")));
        paperwork.setCourt(COURT_REPOSITORY.findById(rs.getLong("court_id")));
        paperwork.setJudge(JUDGE_REPOSITORY.findById(rs.getLong("judge_id")));
        paperwork.setTitle(rs.getString("title"));
        paperwork.setUrl(rs.getString("url"));
        return paperwork;
    }
}
