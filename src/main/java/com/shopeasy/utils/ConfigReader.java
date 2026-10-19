
package com.shopeasy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader() {

        prop = new Properties();

        String configPath = System.getProperty("user.dir")
                + "/src/test/resources/config.properties";
        
        System.out.println("Project directory: "
                + System.getProperty("user.dir"));

        System.out.println("Config path: " + configPath);

        System.out.println("File exists: "
                + new java.io.File(configPath).exists());

        //The resource declared inside try (...) is closed automatically, even if an exception occurs.
        try (FileInputStream input =
                     new FileInputStream(configPath)) {

            prop.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load config.properties", e);
        }
    }

    //{browser=chrome, url=https://www.saucedemo.com/, username=standard_user}
    public Properties getProperties() {
        return prop;
    }

    /* public String getProperty(String key) {

        String value = prop.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Missing property in config.properties: " + key);
        }

        return value.trim();
    }*/
}
