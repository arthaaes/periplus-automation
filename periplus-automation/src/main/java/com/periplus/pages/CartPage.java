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
        clearText(quantityInput);
        writeText(quantityInput, qty);
        click(updateBtn);
    }

    public void removeItem() {
        click(removeBtn);
    }

    public String getSubtotal() {
        return readText(subtotalPrice);
    }

    public boolean isEmptyMessageDisplayed() {
        return readText(emptyMsg).contains("empty");
    }
}