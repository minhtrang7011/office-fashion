package com.poly.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRegisterWithValidInfo() throws InterruptedException {
        driver.get("http://localhost:8080/register");
        Thread.sleep(1000);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmPasswordInput = driver.findElement(By.id("confirmPassword"));
        WebElement registerBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        String uniqueUsername = "user" + System.currentTimeMillis();
        usernameInput.sendKeys(uniqueUsername);
        Thread.sleep(1000);
        passwordInput.sendKeys("123456");
        Thread.sleep(1000);
        confirmPasswordInput.sendKeys("123456");

        Thread.sleep(1000);
        registerBtn.click();

        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("/login"));
    }

    @Test
    public void testRegisterWithDuplicateUsername() throws InterruptedException {
        driver.get("http://localhost:8080/register");
        Thread.sleep(1000);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmPasswordInput = driver.findElement(By.id("confirmPassword"));
        WebElement registerBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("minhtrangn");
        Thread.sleep(1000);
        passwordInput.sendKeys("password_2");
        Thread.sleep(1000);
        confirmPasswordInput.sendKeys("password_2");

        Thread.sleep(1000);
        registerBtn.click();
        Thread.sleep(2000);

        WebElement errorElement = driver.findElement(By.cssSelector(".alert.alert-danger p"));
        String errorText = errorElement.getText();

        Assert.assertTrue(errorText.contains("Tên đăng nhập đã tồn tại"));
    }

    @Test
    public void testRegisterWithInvalidPasswordFail() throws InterruptedException {
        // Mở trang đăng ký
        driver.get("http://localhost:8080/register");
        Thread.sleep(1000);

        // Lấy các yếu tố cần thiết
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmPasswordInput = driver.findElement(By.id("confirmPassword"));
        WebElement registerBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        // Nhập thông tin vào form
        String username = "userfail" + System.currentTimeMillis();
        String weakPassword = "1234";

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(weakPassword);
        confirmPasswordInput.sendKeys(weakPassword);
        Thread.sleep(1000);

        // Nhấn nút đăng ký
        registerBtn.click();
        Thread.sleep(2000);

        // Kiểm tra thông báo lỗi trên trang
        WebElement errorMsg = driver.findElement(By.cssSelector(".alert.alert-danger p"));
        String errorText = errorMsg.getText();

        Assert.assertTrue(errorText.contains("Mật khẩu phải từ 8 ký tự, có 1 chữ in hoa và 1 ký tự đặc biệt"));
    }

    @Test
    public void testRegisterWithEmptyFields() throws InterruptedException {
        driver.get("http://localhost:8080/register");
        Thread.sleep(1000);

        // Tìm các yếu tố cần thiết
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmPasswordInput = driver.findElement(By.id("confirmPassword"));
        WebElement registerBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("");
        passwordInput.sendKeys("");
        confirmPasswordInput.sendKeys("");

        registerBtn.click();
        Thread.sleep(2000);

        WebElement errorMsg = driver.findElement(By.cssSelector(".alert.alert-danger p"));
        String errorText = errorMsg.getText();

        Assert.assertTrue(errorText.contains("Vui lòng điền đầy đủ thông tin"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}