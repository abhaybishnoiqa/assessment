package com.qa.nytimes.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private SafariOptions so;


    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");

        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            co.setPlatformName("linux");
            co.setCapability("enableVNC",true);
            co.setBrowserVersion(prop.getProperty("browserversion"));
        }
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("--incognito");
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            fo.setPlatformName("linux");
            fo.setCapability("enableVNC",true);
            fo.setBrowserVersion(prop.getProperty("browserversion"));
        }

        return fo;
    }

    public SafariOptions getSafariOptions() {
        so = new SafariOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) so.setCapability(prop.getProperty("headless"),"--headless");
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) so.setCapability(prop.getProperty("incognito"),"--incognito");
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            so.setPlatformName("linux");
            so.setCapability("enableVNC",true);
            so.setBrowserVersion(prop.getProperty("browserversion"));
        }
        return so;
    }
}
