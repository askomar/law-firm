package com.solvd.lawfirm.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSessionHolder {

    private static final String CONFIG_FINAL_NAME = "mybatis-config.xml";
    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        SQL_SESSION_FACTORY = buildSessionFactory();
    }

    private static SqlSessionFactory buildSessionFactory() {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(CONFIG_FINAL_NAME);
        } catch (IOException e) {
            throw new RuntimeException("Unable to prepare nybatis config xml.", e);
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(inputStream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SQL_SESSION_FACTORY;
    }
}
