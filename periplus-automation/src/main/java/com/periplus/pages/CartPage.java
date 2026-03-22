package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By cartRows = By.cssSelector(".cart-info tbody tr");
    private By quantityInput = By.cssSelector("input[name*='quantity']");
    private By updateBtn = By.cssSelector(".btn-update");
    private By removeBtn = By.cssSelector(".btn-remove");
    private By subtotalPrice = By.xpath("//td[text()='Sub-Total:']/following-sibling::td");
    private By emptyMsg = By.cssSelector(".content");

    public CartPage(WebDriver driver) { super(driver); }

    public void updateQty(String qty) {
        // This method supports a common shopping cart scenario:
        // user changes quantity, then updates the cart.
        clearText(quantityInput);
        writeText(quantityInput, qty);
        click(updateBtn);
    }

    public void removeItem() {
        // Simple helper for remove-item scenario.
        click(removeBtn);
    }

    public String getSubtotal() {
        // Read current subtotal so it can be checked in assertions later if needed.
        return readText(subtotalPrice);
    }

    public boolean isEmptyMessageDisplayed() {
        // Useful for negative scenario after removing all items from cart.
        return readText(emptyMsg).contains("empty");
    }
}