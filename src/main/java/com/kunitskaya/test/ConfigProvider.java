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

    public String getDefaultTimeout() throws IOException {
        return getProperty("default.timeout.seconds");
    }

    public String getBrowser() throws IOException {
        return getProperty("browser");
    }

    public boolean getIsRemoteDriver() throws IOException {
        return Boolean.parseBoolean(getProperty("is.remote.driver"));
    }
}

//
//public class CrunchifyGetPropertyValues {
//    String result = "";
//    InputStream inputStream;
//
//    public String getPropValues() throws IOException {
//
//        try {
//            Properties prop = new Properties();
//            String propFileName = "config.properties";
//
//            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//
//            if (inputStream != null) {
//                prop.load(inputStream);
//            } else {
//                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//            }
//
//            Date time = new Date(System.currentTimeMillis());
//
//            // get the property value and print it out
//            String user = prop.getProperty("user");
//            String company1 = prop.getProperty("company1");
//            String company2 = prop.getProperty("company2");
//            String company3 = prop.getProperty("company3");
//
//            result = "Company List = " + company1 + ", " + company2 + ", " + company3;
//            System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        } finally {
//            inputStream.close();
//        }
//        return result;
//    }
//}
//
//
/////
//
//    map<String, String> properties = null
//
//    public static getInstance()
//
//if properties=null
//        loadProperties()
//        return ConfigProvider
//
//
//public String getProperty(String propertyName)
//
//
//public String getDefaultTimeout(){
//        return getProperty("defaultTimeout")}
//
//public bolean getIsRemoteWd(){
//        see wdProvider
//        get from there
//        }