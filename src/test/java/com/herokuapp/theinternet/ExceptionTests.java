package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;


public class ExceptionTests {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        // Create driver

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                System.out.println(browser + " browser is started");
                break;
            case "safari":
                driver = new SafariDriver();
                System.out.println(browser + " browser is started");
                break;
            default:
                System.out.println("Don't know how to start " + browser + " started Chrome browser instead.");
                driver = new ChromeDriver();
                break;
        }

        // Open tested page
        String url = "https://practicetestautomation.com/practice-test-exceptions/";
        driver.get(url);

        driver.manage().window().maximize();

        System.out.println("Page is opened");
    }

    @Test
    public void noSuchElementException() {
//        Open page is opened in the before step

//        Click Add button

        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

//        implicitlyWait
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

//        explicitlyWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement rowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        rowElement.sendKeys("Pizza");

//        Verify Row 2 input field is displayed
        Assert.assertTrue(rowElement.isDisplayed(), "Row 2 is not displayed.");



//        Click the Save button
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='rows']/div[3]/div[@class='row']/button[@id='save_btn']"));
        saveButton.click();
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.quit();
        System.out.println("Test is finished");

    }
}
