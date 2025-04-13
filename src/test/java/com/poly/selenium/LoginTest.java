package com.poly.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Thread.sleep(2000);

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("minhtrangn");
        Thread.sleep(1000);
        passwordInput.sendKeys("password_2");
        Thread.sleep(2000);

        loginBtn.click();

        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/home") || currentUrl.contains("/dashboard"));
    }

    @Test
    public void testLoginFailPassword() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Thread.sleep(2000);

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        Thread.sleep(2000);
        usernameInput.sendKeys("minhtrangn");
        Thread.sleep(2000);
        passwordInput.sendKeys("123456");

        Thread.sleep(2000);
        loginBtn.click();

        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Đợi tối đa 10 giây
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-danger")));

        Assert.assertTrue(errorMessage.isDisplayed(), "Lỗi thông báo không hiển thị khi đăng nhập thất bại.");
    }

    @Test
    public void testLoginWithUnregisteredUsername() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Thread.sleep(2000);

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("tamle");
        Thread.sleep(2000);

        passwordInput.sendKeys("123456");
        Thread.sleep(2000);

        Thread.sleep(2000);
        loginBtn.click();

        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.cssSelector("p.text-danger"));
        String errorText = errorMessage.getText();

        Assert.assertEquals(errorText, "Tên đăng nhập không tồn tại");
    }

    @Test
    public void testLoginWithShortPassword() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        Thread.sleep(2000);
        usernameInput.sendKeys("hoavo");
        Thread.sleep(2000);
        passwordInput.sendKeys("123");

        Thread.sleep(2000);
        loginBtn.click();

        Thread.sleep(2000);

        WebElement errorMessage = driver.findElement(By.cssSelector(".text-danger.text-center"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Mật khẩu phải có ít nhất 6 ký tự");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"));
    }

    @Test
    public void testLoginWithEmptyFields() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        Thread.sleep(2000);

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.clear();
        passwordInput.clear();

        loginBtn.click();

        Thread.sleep(2000);

        WebElement errorMessage = driver.findElement(By.cssSelector(".text-danger.text-center"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Tên đăng nhập và mật khẩu không được để trống");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
