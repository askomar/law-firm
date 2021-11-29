package com.solvd.lawfirm.persistence;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public enum Config {
    DRIVER, USERNAME, PASSWORD, URL, POOL_SIZE;

    private String value;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
            DRIVER.value = properties.getProperty(DRIVER.toString().toLowerCase(Locale.ROOT));
            USERNAME.value = properties.getProperty(USERNAME.toString().toLowerCase(Locale.ROOT));
            PASSWORD.value = properties.getProperty(PASSWORD.toString().toLowerCase(Locale.ROOT));
            URL.value = properties.getProperty(URL.toString().toLowerCase(Locale.ROOT));
            POOL_SIZE.value = properties.getProperty(POOL_SIZE.toString().toLowerCase(Locale.ROOT));
        } catch (IOException ignored) {
            System.err.println("ERROR");
        }
    }

    public String getValue() {
        return this.value;
    }

    Config() {
    }
}
