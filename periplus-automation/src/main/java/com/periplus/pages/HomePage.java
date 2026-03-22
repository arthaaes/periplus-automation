package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By searchBox = By.cssSelector("input[name='filter_name']");
    private By cartIcon = By.id("cart-total");
    private By logoutBtn = By.linkText("Logout");

    public HomePage(WebDriver driver) { super(driver); }

    public void searchProduct(String name) {
        // Search is one of the main entry points to find a product quickly,
        // so I wrapped it in one method to keep the test readable.
        writeText(searchBox, name);
        
        // Press Enter to submit search instead of looking for a separate search button.
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public void goToCart() {
        // Click cart icon when I want to move from browsing to cart validation.
        click(cartIcon);
    }

    public int getCartBadgeCount() {
        // The cart text can contain other characters, so I keep only digits.
        // Example idea: "1 item(s) - Rp..." becomes just the number part.
        String text = readText(cartIcon).replaceAll("[^0-9]", "");
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }
    
    public void logout() {
        // Helper for logout scenario if I extend the suite later.
        click(logoutBtn);
    }
}