
package com.shopeasy.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static void captureScreenshot(
            WebDriver driver, String screenshotName) {

        if (driver == null) {
            System.out.println(
                "Screenshot skipped: WebDriver is null.");
        }

        if (!(driver instanceof TakesScreenshot)) {
            System.out.println(
                "Screenshot skipped: Driver does not support screenshots.");
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            File folder = new File("screenshots");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            
            String timestamp = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter
                    .ofPattern("yyyyMMdd_HHmmss"));

            File destination =
                new File(folder, screenshotName +timestamp+ ".png");

            FileUtils.copyFile(source, destination);

            System.out.println(
                "Screenshot saved: " + destination.getAbsolutePath());


        } catch (Exception e) {
            System.err.println(
                "Could not capture screenshot: " + e.getMessage());
        }
    }
}
