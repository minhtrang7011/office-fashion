package com.poly.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginSuccess() {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("minhtrangn");
        passwordInput.sendKeys("password_2");
        loginBtn.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/home") || currentUrl.contains("/dashboard"));
    }

    @Test
    public void testLoginFail() {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("minhtrangn");
        passwordInput.sendKeys("123");// sai pass
        loginBtn.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/home") || currentUrl.contains("/dashboard"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
