package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMinutes(1));
    }

    public void closePopupsIfPresent() {
        try {
            // FIX: Temporarily turn off the 15-second implicit wait.
            // This makes the popup check instant so it doesn't freeze your login!
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            
            By closeButton = By.cssSelector(".p-close, .close-button, #popup-close");
            if (driver.findElements(closeButton).size() > 0) {
                System.out.println("DEBUG: Popup detected! Closing it...");
                driver.findElement(closeButton).click();
            }
        } catch (Exception e) {
            System.out.println("DEBUG: No popup found or couldn't close it.");
        } finally {
            // Turn the 15-second wait back on for the rest of the test
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    }

    // A helper method to wait for the annoying loading screen to disappear
    protected void waitForPreloader() {
        try {
            // Temporarily turn off implicit wait here too, to prevent delays if preloader isn't there
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".preloader")));
        } catch (Exception e) {
            // Ignore if it's already gone
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    }

    protected void click(By locator) {
        waitForPreloader();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void writeText(By locator, String text) {
        System.out.println("DEBUG: Waiting for visibility of: " + locator.toString());
        
        // 1. Wait for the loading screen to vanish
        waitForPreloader();
    
        // 2. Wait for the text box to be visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    
        // 3. Clear and type
        element.clear();
        System.out.println("DEBUG: Typing text into: " + locator.toString());
        element.sendKeys(text);
    }

    protected String readText(By locator) {
        waitForPreloader();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected void clearText(By locator) {
        waitForPreloader();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
    }
}