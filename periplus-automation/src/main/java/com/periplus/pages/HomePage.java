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
        // 1. Type the name into the box
        writeText(searchBox, name);
        
        // FIX: Press the ENTER key on the keyboard instead of clicking the search button
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public int getCartBadgeCount() {
        String text = readText(cartIcon).replaceAll("[^0-9]", "");
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }
    
    public void logout() {
        click(logoutBtn);
    }
}