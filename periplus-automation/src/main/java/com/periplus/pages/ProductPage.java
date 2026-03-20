package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    // FIX: Look for the button by its exact visible text or standard ID
    private By addToCartBtn = By.xpath("//*[@id='button-cart'] | //*[normalize-space()='ADD TO CART'] | //*[normalize-space()='Add to Cart']");
    
    private By productTitle = By.cssSelector("h1");
    private By quantityField = By.name("quantity");
    private By stockStatus = By.cssSelector(".stock-status");

    public ProductPage(WebDriver driver) { super(driver); }

    public String getTitle() { return readText(productTitle); }

    public void setQuantity(String qty) {
        clearText(quantityField);
        writeText(quantityField, qty);
    }

    public void addToCart() {
        click(addToCartBtn);
    }
    
    public String getStockWarning() {
        return readText(stockStatus);
    }
}