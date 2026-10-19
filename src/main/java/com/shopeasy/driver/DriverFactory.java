
package com.shopeasy.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {

        if (browserName == null || browserName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Browser name is missing in config.properties");
        }

        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")
                || browserName.equalsIgnoreCase("FF")) {

            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();

        } else {

            throw new IllegalArgumentException(
                    "Unsupported browser: " + browserName);
        }
    }
}
