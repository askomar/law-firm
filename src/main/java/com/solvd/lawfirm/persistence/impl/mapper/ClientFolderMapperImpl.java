package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.ClientFolder;
import com.solvd.lawfirm.persistence.ClientFolderRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientFolderMapperImpl implements ClientFolderRepository {

    private static final ClientFolderMapperImpl INSTANCE = new ClientFolderMapperImpl();

    public static ClientFolderMapperImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(ClientFolder clientFolder) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientFolderRepository clientFolderRepository = session.getMapper(ClientFolderRepository.class);
            clientFolderRepository.create(clientFolder);
        }
    }

    @Override
    public List<ClientFolder> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientFolderRepository clientFolderRepository = session.getMapper(ClientFolderRepository.class);
            return clientFolderRepository.findAll();
        }
    }

    @Override
    public ClientFolder findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientFolderRepository clientFolderRepository = session.getMapper(ClientFolderRepository.class);
            return clientFolderRepository.findById(id);
        }
    }

    @Override
    public int update(ClientFolder clientFolder) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientFolderRepository clientFolderRepository = session.getMapper(ClientFolderRepository.class);
            return clientFolderRepository.update(clientFolder);
        }
    }

    @Override
    public int delete(ClientFolder clientFolder) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientFolderRepository clientFolderRepository = session.getMapper(ClientFolderRepository.class);
            return clientFolderRepository.delete(clientFolder);
        }
    }
}
