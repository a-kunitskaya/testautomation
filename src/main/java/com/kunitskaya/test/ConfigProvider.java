package com.kunitskaya.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;

    private ConfigProvider() {

    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }

    private String getProperty(String property) throws IOException {
        Properties properties = new Properties();
        String propertiesFile = "test.properties";
        InputStream inputStream = null;
        String value = "";

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);
            properties.load(inputStream);
            value = properties.getProperty(property);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
        return value;
    }

    public int getDefaultTimeout() throws IOException {
        return Integer.parseInt(getProperty("default.timeout.seconds"));
    }

    public String getBrowser() throws IOException {
        return getProperty("browser");
    }

    public boolean getIsRemoteDriver() throws IOException {
        return Boolean.parseBoolean(getProperty("is.remote.driver"));
    }
}
