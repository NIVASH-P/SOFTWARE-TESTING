package com.cc1;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports extent;
    ExtentSparkReporter sparkReporter;
    JavascriptExecutor js;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Logger logger = LogManager.getLogger(AppTest.class);
    @BeforeTest
    public void beforeTest() throws IOException
    {
        driver = new ChromeDriver();
        extent= new ExtentReports();
        sparkReporter = new ExtentSparkReporter("C:\\Users\\Niwaz\\OneDrive\\Desktop\\Testing\\cc1\\cc1\\src\\main\\java\\com\\cc1\\Report.Html");
        extent.attachReporter(sparkReporter);
        js = (JavascriptExecutor)driver;
        FileInputStream fis = new FileInputStream("C:\\Users\\Niwaz\\OneDrive\\Desktop\\Testing\\cc1\\cc1\\src\\Book1.xlsx");
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet("Sheet1");  
    }

    @Test
    public void test1()
    {
        logger.info("Login");
        ExtentTest test1 = extent.createTest("test1");
        driver.get("https://www.barnesandnoble.com/");
        String name = sheet.getRow(1).getCell(0).getStringCellValue();
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(name);;
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/span/button")).click();
        String text = driver.findElement(By.className("result-show")).getText();
        if(text.contains("Chetan Bhagat"))
        {
            test1.log(Status.PASS, "Login Successful");
            System.out.println("Successful");
            logger.info("Login SuccessFul");
        }
        else
        {
            test1.log(Status.FAIL, "Login Failed");
            System.out.println("Failed");
            logger.info("Login Failure");
        }
        extent.flush();
    }
    @Test
    public void test2()  throws InterruptedException
    {
        logger.info("Added to Cart");
        driver.get("https://www.barnesandnoble.com/");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/a"))).perform();
        driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[4]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        logger.info("Things Added to Cart");

    }
    @Test(dependsOnMethods = "test2")
    public void test3() throws IOException,InterruptedException
    {
        logger.info("MemberShip");
        driver.get("https://www.barnesandnoble.com/");
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("/html/body/div[4]/div/dd/div/div/footer"))).perform();;
        driver.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]")).click();
        actions.scrollToElement(driver.findElement(By.xpath("/html/body/main/section/div[1]/div[2]/div/div/div[4]"))).perform();;
        driver.findElement(By.linkText("JOIN REWARDS")).click();
        Thread.sleep(5000);
        File file =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("./screenshots.jpeg"));
        logger.info("MemberShip Activated Successfully");
    }

    @AfterTest()
    public void afterTest()
    {

    }
}
