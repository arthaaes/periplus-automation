package com.periplus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // These use 'name' and 'type' which are less likely to change than IDs
    private By emailField = By.cssSelector("input[name='email']");
    private By passwordField = By.cssSelector("input[name='password']");
    private By loginBtn = By.cssSelector("input[type='submit'], button[type='submit']"); 

    public LoginPage(WebDriver driver) { super(driver); }

    public void login(String email, String pass) {
        System.out.println("DEBUG: Looking for email field...");
        writeText(emailField, email);
        
        System.out.println("DEBUG: Email entered. Looking for password field...");
        writeText(passwordField, pass);
        
        System.out.println("DEBUG: Clicking login button...");
        click(loginBtn);
    }
}