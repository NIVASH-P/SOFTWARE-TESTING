package com.cc1;


import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.reactivex.rxjava3.functions.Action;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports extent;
    ExtentSparkReporter sparkReporter;
    JavascriptExecutor js;
    @BeforeTest
    public void beforeTest()
    {
        driver = new ChromeDriver();
        extent= new ExtentReports();
        sparkReporter = new ExtentSparkReporter("C:\\Users\\Niwaz\\OneDrive\\Desktop\\Testing\\cc1\\cc1\\src\\main\\java\\com\\cc1\\Report.Html");
        extent.attachReporter(sparkReporter);
        js = (JavascriptExecutor)driver;
    }

    @Test
    public void test1()
    {
        ExtentTest test1 = extent.createTest("test1");
        driver.get("https://www.barnesandnoble.com/");
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys("Chetan Bhagat");;
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/span/button")).click();
        String text = driver.findElement(By.className("result-show")).getText();
        if(text.contains("Chetan Bhagat"))
        {
            test1.log(Status.PASS, "Login Successful");
            System.out.println("Successful");
        }
        else
        {
            test1.log(Status.FAIL, "Login Failed");
            System.out.println("Failed");
        }
        extent.flush();


    }
    @Test
    public void test2()
    {
        ExtentTest test1 = extent.createTest("test1");
        driver.get("https://www.barnesandnoble.com/");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a")));
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        

    }
    @Test
    public void test3()
    {
        ExtentTest test1 = extent.createTest("test1");
        driver.get("https://www.barnesandnoble.com/");
        Actions actions = new Actions(driver);
        // actions.scrollToElement(driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]")));
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]")).click();
    }

    @AfterTest()
    public void afterTest()
    {

    }
}
