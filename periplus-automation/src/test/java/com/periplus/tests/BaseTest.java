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
        // This is my base setup for every test.
        // Because this is my first time writing Java automation, I tried to put the repeated browser setup here
        // so the actual test file can stay focused on the business flow.

        // I use WebDriverManager so I do not need to manually download and match the ChromeDriver version myself.
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Start browser in maximized mode so elements are less likely to shift because of small window size.
        options.addArguments("--start-maximized");

        // I disable notifications because they can cover buttons or input fields during automation.
        options.addArguments("--disable-notifications");
        
        // I am living in China right now, so to access Periplus I need to use a VPN server from Indonesia.
        // The connection is noticeably slow, so I do not want the test to wait for every single asset on the page
        // like images or other non-critical resources.
        // Because of that, I use EAGER page load strategy to continue once the main HTML is ready enough to interact.
        // From QA perspective, this is one small way to improve time efficiency on a slow network. 
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        
        // I gave page loading a long timeout because VPN traffic can sometimes be unstable and much slower than normal.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3));

        // I still keep a reasonable implicit wait for common element lookups.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void teardown() {
        // Always close the browser after each test so the next run starts clean.
        if (driver != null) {
            driver.quit();
        }
    }
}