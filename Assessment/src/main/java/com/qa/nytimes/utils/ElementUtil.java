package com.qa.nytimes.utils;

import com.qa.nytimes.factory.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil {

    private WebDriver driver;
    private JavaScriptUtil jsUtil;
    public static final Logger log = Logger.getLogger(ElementUtil.class);

    public ElementUtil(WebDriver driver) {
        this.driver=driver;
        jsUtil= new JavaScriptUtil(driver);
    }

    public By getBy(String locatorType, String locatorValue) {
        By locator = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                locator = By.id(locatorValue);
                break;

            case "name":
                locator = By.name(locatorValue);
                break;

            case "classname":
                locator = By.className(locatorValue);
                break;

            case "xpath":
                locator = By.xpath(locatorValue);
                break;

            case "cssselector":
                locator = By.cssSelector(locatorValue);
                break;

            case "linktext":
                locator = By.linkText(locatorValue);
                break;

            case "partiallinktext":
                locator = By.partialLinkText(locatorValue);
                break;

            case "tagname":
                locator = By.tagName(locatorValue);
                break;

            default:
                break;
        }
        return locator;

    }

    public WebElement getElement(By locator){
        WebElement element = driver.findElement(locator);
        return element;
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void doSendKeys(By locator,String value){
        log.info("locator is : " + locator + " value "+ value);
        WebElement ele = getElement(locator);
        ele.clear();
        ele.sendKeys(value);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public String doElementGetText(By locator) {
        return getElement(locator).getText();
    }

    public boolean doIsDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }
    public void doContextClick(By locator) {
        Actions act = new Actions(driver);
        act.contextClick(getElement(locator)).perform();
    }

    public List<String> getRightClickOptionsList(By rightClick, By rightClickOptions) {
        List<String> rightClickItems = new ArrayList<String>();
        doContextClick(rightClick);
        List<WebElement> itemsList = getElements(rightClickOptions);
        System.out.println(itemsList.size());

        for (WebElement e : itemsList) {
            String text = e.getText();
            //System.out.println(text);
            rightClickItems.add(text);
        }
        return rightClickItems;
    }


    /**
     * Clicks in the middle of the given element. Equivalent to: Actions.moveToElement(onElement).click()
     * @param locator
     */
    public void doActionsClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).perform();
    }

    public String waitForUrl(int timeOut, String urlFraction) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
            return driver.getCurrentUrl();
        }
        return null;
    }

    public String waitForTitleIs(int timeOut, String titleVal) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleIs(titleVal))) {
            return driver.getTitle();
        } else {
            System.out.println("title is not correct.....");
            return null;
        }
    }

    public List<String> getElementsTextListWithWait(By locator, int timeOut) {
        List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
        List<String> eleTextList = new ArrayList<String>();
        for (WebElement e : eleList) {
            String text = e.getText();
            eleTextList.add(text);
        }
        return eleTextList;
    }

    public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


}
