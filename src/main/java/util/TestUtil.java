package util;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtil extends TestBase {

    public static String createRandomIntBetween(int start, int end) {
        return String.valueOf(start + Math.round(Math.random() * (end - start)));
    }

    public static String createRandomEmail() {
        String randomInt = createRandomIntBetween(0, 1000);
        return "brilliant" + randomInt + "@mail.com";
    }

    public static String createRandomPassword(int chars) {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( chars, validChars );
    }

    public static String createRandomName(int chars) {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return RandomStringUtils.random( chars, validChars );
    }

    public static void scrollToBottom(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + dateFormat + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
