package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader instance; // singleton instance
    private Properties prop;

    // private constructor prevents direct instantiation
    private ConfigReader() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // lazy initialization with synchronized for thread safety
    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }
}
