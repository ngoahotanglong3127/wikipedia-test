/* (C)2023 */
package com.common.ui.basesetup;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseSetup {
    private static WebDriver driver;
    static String chromeDriverPath =
            "/Users/thituyethanh.truong/Documents/Softwares/chromedriver-mac-x64/chromedriver";

    public WebDriver getDriver() {
        return driver;
    }

    private static void setDriver() {
        driver = initChromeDriver();
    }

    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(com.common.ui.Constant.BASE_URL);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    @BeforeClass
    public static void initializeTestBaseSetup() {
        try {
            setDriver();
        } catch (Exception e) {
            System.out.println("Error..." + Arrays.toString(e.getStackTrace()));
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        //        Thread.sleep(2000);
        //        driver.quit();
    }
}
