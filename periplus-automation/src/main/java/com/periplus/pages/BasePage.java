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

        // I use one shared explicit wait in the base page so all child pages can reuse it.
        // I intentionally made it longer because the target website is slower from my location 
        // (In China, I need to use VPN with "Indonesia" server to be able to access Periplus,
        // and it's extremely slow).
        this.wait = new WebDriverWait(driver, Duration.ofMinutes(1));
    }

    public void closePopupsIfPresent() {
        try {
            // Here I temporarily turn off implicit wait.
            // My reason is: if no popup exists, I do not want Selenium to waste extra waiting time searching for it.
            // Since I am in China and need an Indonesia VPN to access Periplus, every unnecessary wait feels longer,
            // so I tried to make these helper steps more time-efficient.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            
            By closeButton = By.cssSelector(".p-close, .close-button, #popup-close");
            if (driver.findElements(closeButton).size() > 0) {
                System.out.println("DEBUG: Popup detected! Closing it...");
                driver.findElement(closeButton).click();
            }
        } catch (Exception e) {
            System.out.println("DEBUG: No popup found or couldn't close it.");
        } finally {
            // Restore the default implicit wait for normal test steps.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    }

    // A helper method to wait for the annoying loading screen to disappear
    protected void waitForPreloader() {
        try {
            // Same idea here: I disable implicit wait first so the code does not get delayed
            // when the preloader is already gone.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            
            // This helper is useful because the site sometimes shows a loading layer before elements are clickable.
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".preloader")));
        } catch (Exception e) {
            // If preloader does not exist or is already invisible, I simply continue.
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    }

    protected void click(By locator) {
        // Before clicking, I wait for loading overlay to disappear.
        waitForPreloader();

        // Then I wait until the target element is actually clickable.
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void writeText(By locator, String text) {
        System.out.println("DEBUG: Waiting for visibility of: " + locator.toString());
        
        // First make sure the page is not still covered by loading animation.
        waitForPreloader();
    
        // Then wait until the field is visible.
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    
        // I clear first so old text does not stay in the field.
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