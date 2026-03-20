package com.periplus.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Step 1: Open Google Chrome in a new window
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        // FIX 1: The magic setting for slow VPNs. This forces Chrome to stop loading 
        // heavy images/trackers and just give us the HTML we need to test.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        
        // FIX 2: Give the slow VPN up to 3 minutes to establish a connection
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // FIX 3: Removed the driver.get() from here so we don't load the site twice!
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}