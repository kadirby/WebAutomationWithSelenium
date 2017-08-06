package unitTests;


import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;



import org.apache.commons.io.FileUtils;

import org.openqa.selenium.remote.Augmenter;



public class TestSample2 {

    WebDriver driver = new SafariDriver();

    @Test

    public void test() throws InterruptedException {

        driver.manage().window().maximize();
        // Get URL ( Navigate N11.com )

        driver.get("http://www.n11.com");
        Thread.sleep(2000);
        // Find "Giriş Yap" Link By Class & Click

        //System.out.print(driver.findElement(By.className("btnSignIn")).getAttribute("href"));

        driver.findElement(By.className("btnSignIn")).click();
        Thread.sleep(2000);

        // Find "Facebook ile Giriş Yap" Link & Click
        //driver.get("http://www.n11.com/giris-yap");
        driver.findElement(By.xpath("//div[contains(concat(' ', @class, ' '), ' facebookBtn ')]")).click();

        Thread.sleep(5000);

        // Facebook Login
        driver.findElement(By.id("email")).sendKeys("testuser");
        driver.findElement(By.id("pass")).sendKeys("Test@123");
        driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);

        // Find Search Area

        Thread.sleep(10000);

        WebElement productSearchForm = driver.findElement(By.id("searchData"));
        System.out.print(productSearchForm);

        // Make Search

        productSearchForm.clear();
        productSearchForm.sendKeys("Iphone 7");
        productSearchForm.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        // Get First Link Content

        String FirstLinkProductName = driver.findElement(By.xpath("//div[@data-position='1']/div/a/h3")).getText();
        String FirstLinkProductPrice = driver.findElement(By.xpath("//div[@data-position='1']/div/a/ins")).getText();


        System.out.print(FirstLinkProductName + FirstLinkProductPrice);

        String Link = driver.findElement(By.xpath("//div[@data-position='1']/div/a")).getAttribute("href");
        Thread.sleep(5000);
        System.out.print(Link);
        driver.get(Link);


        // Get Detail Link Contents

        Thread.sleep(5000);


        String DetailLinkProductName = driver.findElement(By.xpath("//div[@class='nameHolder']/h1")).getText();

        String DetailLinkProductPrice = driver.findElement(By.xpath("//div[@class='newPrice']/ins")).getText();

        // Check First Link & Detail Link Contents

        if ((DetailLinkProductName == FirstLinkProductName) && (DetailLinkProductPrice == FirstLinkProductPrice));
        {
            {
                try {
                    // Get Screenshot
                    WebDriver augmentedDriver = new Augmenter().augment(driver);
                    File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
                    File file = new File("/Users/kadirby/Documents/n11.jpeg");
                    FileUtils.copyFile(screenshot, file);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Log out

        driver.get(driver.findElement(By.xpath("//a[@class='logoutBtn']")).getAttribute("href"));

        // Close browser

        Thread.sleep(2000);
        driver.quit();

    }

}