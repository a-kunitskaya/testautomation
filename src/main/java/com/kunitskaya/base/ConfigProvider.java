package com.kunitskaya.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;

    private static final String TEST_PROPERTIES_FILE = "test.properties";
    private Properties properties = new Properties();

    private ConfigProvider() {
        loadProperties();
    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            //passing input stream as a parameter to get it closed automatically
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TEST_PROPERTIES_FILE)) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProperty(String property) {
        return properties.getProperty(property);
    }

    public int getDefaultTimeout() {
        return Integer.parseInt(getProperty("default.timeout.seconds"));
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean isRemoteDriver() {
        return Boolean.parseBoolean(getProperty("is.remote.driver"));
    }

    public String getPlatform() {
        return getProperty("platform");
    }

    public String getHubUrl() {
        return getProperty("selenium.grid.hub.url");
    }

    public String getBaseWsTestUrl() {
        return getProperty("base.ws.test.url");
    }

    public String getWsUsersUri() {
        return getProperty("ws.users.uri");
    }

    public String getToWsDoUri(){
        return getProperty("ws.todo.uri");
    }
}
