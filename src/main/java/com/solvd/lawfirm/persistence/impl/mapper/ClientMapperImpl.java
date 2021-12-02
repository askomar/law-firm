package com.solvd.lawfirm.persistence.impl.mapper;

import com.solvd.lawfirm.domain.Client;
import com.solvd.lawfirm.persistence.ClientRepository;
import com.solvd.lawfirm.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientMapperImpl implements ClientRepository {

    private static final ClientMapperImpl INSTANCE = new ClientMapperImpl();

    public static ClientMapperImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Client client) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            clientRepository.create(client);
        }
    }

    @Override
    public List<Client> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            return clientRepository.findAll();
        }
    }

    @Override
    public Client findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            return clientRepository.findById(id);
        }
    }

    @Override
    public int update(Client client) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            return clientRepository.update(client);
        }
    }

    @Override
    public int delete(Client client) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = session.getMapper(ClientRepository.class);
            return clientRepository.update(client);
        }
    }
}
