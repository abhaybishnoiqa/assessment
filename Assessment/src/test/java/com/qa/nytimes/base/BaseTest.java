package com.qa.nytimes.base;

import com.qa.nytimes.factory.DriverFactory;
import com.qa.nytimes.pages.*;
import com.qa.nytimes.utils.ElementUtil;
import com.qa.nytimes.utils.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    public DriverFactory df;
    public Properties prop;
    public WebDriver driver;
    public HomePage homePage;

    @BeforeMethod
    public void setUp(){
        df = new DriverFactory();
        prop = df.init_prop();
        driver = df.init_driver(prop);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
