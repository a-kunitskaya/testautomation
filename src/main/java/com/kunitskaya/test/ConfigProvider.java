package com.kunitskaya.test;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;

    private static final String PROPERTIES_FILE = "test.properties";

    private ConfigProvider() {

    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }

    private String getProperty(String property) {
        Properties properties = new Properties();
        String value = "";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            properties.load(inputStream);
            value = properties.getProperty(property);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public int getDefaultTimeout() {
        return Integer.parseInt(getProperty("default.timeout.seconds"));
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean getIsRemoteDriver() {
        return Boolean.parseBoolean(getProperty("is.remote.driver"));
    }
}
