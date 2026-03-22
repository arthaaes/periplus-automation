package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
     // I made this locator a little flexible because websites sometimes change button text casing.
    // So here I try to support id-based locating first, then fallback text-based locating.
    private By addToCartBtn = By.xpath("//*[@id='button-cart'] | //*[normalize-space()='ADD TO CART'] | //*[normalize-space()='Add to Cart']");
    
    private By productTitle = By.cssSelector("h1");
    private By quantityField = By.name("quantity");
    private By stockStatus = By.cssSelector(".stock-status");

    public ProductPage(WebDriver driver) { super(driver); }

    // Read product title in case I want stronger assertion between product page and cart page.
    public String getTitle() { return readText(productTitle); }

    public void setQuantity(String qty) {
        // Reusable helper for quantity-related scenarios.
        clearText(quantityField);
        writeText(quantityField, qty);
    }

    public void addToCart() {
        // Core action for this assignment scenario.
        click(addToCartBtn);
    }
    
    public String getStockWarning() {
        // Can be used later for out-of-stock or limited-stock validation.
        return readText(stockStatus);
    }
}