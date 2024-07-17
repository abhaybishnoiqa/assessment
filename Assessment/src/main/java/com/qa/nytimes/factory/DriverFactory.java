package com.qa.nytimes.factory;

import com.qa.nytimes.utils.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    public Properties prop;
    public OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public static final Logger log = Logger.getLogger(DriverFactory.class);

    /**
     * This method is used to initialize the webdriver on the basis of given browser
     * name. This method will take care of local and remote execution
     *
     * @param prop
     * @return
     */
        public WebDriver init_driver(Properties prop) {

            String browserName = prop.getProperty("browser").trim();
            log.info("browser name is: " +browserName);
            optionsManager = new OptionsManager(prop);

            if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
                log.info("running on chrome browser");

                if(Boolean.parseBoolean(prop.getProperty("remote"))){
                    init_remoteWebdriver(Browser.CHROME_BROWSER_VALUE);
                }
                else {
                    WebDriverManager.chromedriver().setup();
                    tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                }
            }
            else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
                if(Boolean.parseBoolean(prop.getProperty("remote"))){
                    init_remoteWebdriver(Browser.FIREFOX_BROWSER_VALUE);
                }else {
                    WebDriverManager.firefoxdriver().setup();
                    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                }
            } else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE))
                if(Boolean.parseBoolean(prop.getProperty("remote")))
                {
                    init_remoteWebdriver(Browser.SAFARI_BROWSER_VALUE);
                }else {
                    WebDriverManager.safaridriver().setup();
                    tlDriver.set(new SafariDriver(optionsManager.getSafariOptions()));
                } else {
                System.out.println("Browser name is not correct..");
            }

            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().maximize();
            getDriver().get(prop.getProperty("url"));
            log.info(prop.getProperty("url") + " ..url is launched");

            return getDriver();

        }

    /**
     * This method is used to run tests on remote - Docker Machine
     * @param browserName
     */
    private void init_remoteWebdriver(String browserName) {
        System.out.println("Running TC on remote grid server : " +browserName);
        if (browserName.equalsIgnoreCase("chrome")){
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
         * this will return the thread local copy of the webdriver(driver)
         *
         * @return
         */
        public static WebDriver getDriver() {
            return tlDriver.get();
        }

        /**
         * This method is used to initialize the properties
         *
         * @return this returns prop
         */
        public Properties init_prop() {
            prop = new Properties();
            FileInputStream ip = null;
                try {
                    log.info("Reading the properties file");
                    ip = new FileInputStream("./src/test/resources/config/config.properties");
                    prop.load(ip);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return prop;
        }


        /**
         * take screenshot
         */
        public static String getScreenshot(){
            File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
            File destination=new File(path);
            try {
                FileUtils.copyFile(srcFile,destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;


        }

    }

