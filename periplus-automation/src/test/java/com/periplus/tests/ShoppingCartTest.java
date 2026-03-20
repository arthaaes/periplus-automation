package com.periplus.tests;

import com.periplus.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ShoppingCartTest extends BaseTest {

    @Test(description = "Scenario B Part 2.2: Add one product to the cart")
    public void testAddToCartScenario() {
        // Step 3: Enter a login and password
        driver.get("https://www.periplus.com/index.php?route=account/login");
        LoginPage login = new LoginPage(driver);
        login.closePopupsIfPresent();
        login.login("sidaurukartha890@gmail.com", "Artha_020977");

        // Wait for login to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("index"));

        // Navigate to Homepage
        driver.get("https://www.periplus.com/");

        // Step 4: Find one product
        HomePage home = new HomePage(driver);
        home.searchProduct("Effective Java"); 

        // Click the book in search results to open the product page
        WebElement bookLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Effective Java")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", bookLink);

        // Step 5: Add one product to the cart
        ProductPage product = new ProductPage(driver);
        String expectedTitle = "Effective Java"; 
        product.addToCart();

        // FIX: Wait 3 seconds for the server to finish adding the item over the VPN.
        // Then, bypass the blocked UI icon by going straight to the cart URL!
        try { Thread.sleep(3000); } catch (Exception e) {}
        driver.get("https://www.periplus.com/index.php?route=checkout/cart");

        // Step 6: Verify that the product has been successfully added to the cart
        CartPage cart = new CartPage(driver);
        
        Assert.assertTrue(driver.getPageSource().contains(expectedTitle), 
            "Verification Failed: Product '" + expectedTitle + "' was not found in the cart.");
    }
}