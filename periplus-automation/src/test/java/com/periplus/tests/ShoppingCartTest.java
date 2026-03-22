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
        // For this task I chose to go directly to the login page first.
        // It is simpler and reduces extra clicks compared with starting from the homepage.
        driver.get("https://www.periplus.com/index.php?route=account/login");

        // I keep login actions inside LoginPage so the test reads more like business steps.
        LoginPage login = new LoginPage(driver);
        login.closePopupsIfPresent();
        login.login("sidaurukartha890@gmail.com", "Artha_020977");

        // After login, I wait until the URL changes as a simple sign that the request finished successfully.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("index"));

        // Then I move to the homepage because the next scenario is searching for a product.
        driver.get("https://www.periplus.com/");

        // Search for one product.
        // I used "Effective Java" because it is easy to identify in the result and fits this Java-based task.
        HomePage home = new HomePage(driver);
        home.searchProduct("Effective Java"); 

        // I wait for the product link to appear in search result.
        WebElement bookLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Effective Java")));

        // I click using JavaScript here because during my trial runs, normal click was sometimes not reliable.
        // Since I am accessing Periplus from China through an Indonesia VPN server, the page can load slowly
        // and behave a bit inconsistently. I learned that JS click can help when Selenium thinks the element
        // is there but normal clicking is still blocked by rendering timing.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", bookLink);

        // Once I am on the product page, I add the item to cart.
        ProductPage product = new ProductPage(driver);
        String expectedTitle = "Effective Java"; 
        product.addToCart();

        /// I added a short pause here after Add to Cart.
        // Normally I know Thread.sleep is not the best long-term solution, but in this case I kept it because
        // the site response through VPN can be very slow and I wanted a simple, stable wait for this hometask.
        // As a QA engineer, my goal here is to make the scenario reliable enough first, then improve it later
        // with a more specific explicit wait if needed.
        try { 
            Thread.sleep(3000); 
        } catch (Exception e) {
            // I keep the catch minimal here because this is just a temporary stabilization pause for the demo test.
        }

        // Open cart page directly so I can verify the product was really added.
        driver.get("https://www.periplus.com/index.php?route=checkout/cart");

        // I instantiate CartPage for page object consistency, even though this final assertion checks page source.
        CartPage cart = new CartPage(driver);
        
        // Main verification:
        // if the expected product title appears in cart page source, I treat the add-to-cart flow as successful.
        Assert.assertTrue(driver.getPageSource().contains(expectedTitle), 
            "Verification Failed: Product '" + expectedTitle + "' was not found in the cart.");
    }
}