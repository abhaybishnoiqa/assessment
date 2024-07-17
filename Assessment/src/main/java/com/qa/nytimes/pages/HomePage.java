package com.qa.nytimes.pages;

import com.qa.nytimes.utils.Constants;
import com.qa.nytimes.utils.ElementUtil;
import com.qa.nytimes.utils.ExcelUtil;
import com.qa.nytimes.utils.JavaScriptUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil jsUtil;
    private ExcelUtil excelUtil;

    //1.private By Locators
    private By loginBtn = By.xpath("//*[text()='Log in']");

    private By navDropDown = By.xpath("//*[@data-testid='masthead-nested-nav']//li[@data-testid='nav-item-U.S.']");
    private By searchIcon = By.xpath("//*[@data-testid=\"search-button\"]");
    private By searchBar = By.xpath("//*[@data-testid=\"search-input\"]");
    private By goButton = By.xpath("//*[@data-testid=\"search-submit\"]");
    private By newsCount = By.xpath("//*[@data-testid='search-bodega-result']");
    private By footerTitles = By.xpath("//*[@data-testid=\"site-index-section-list\"]/../h3");
    private By langauage = By.xpath("//*[@lang='es-ES']");

    //1.Initialize the driver
    public HomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
        jsUtil = new JavaScriptUtil(driver);
        excelUtil=new ExcelUtil();

    }

    //2.public page actions
    @Step("Getting home page title...")
    public String getHomePageTitle() {
        return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.HOME_PAGE_TITLE);
    }

    @Step("Getting Us page URL...")
    public String getUSPageURL() {
        return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.US_PAGE_URL);
    }

    @Step("Checking if login button exist or not...")
    public boolean isLoginLinkExist() {
        return eleUtil.doIsDisplayed(loginBtn);
    }

    @Step("Clicking on US Nav")
    public void clickOnUSNav() {
        eleUtil.doClick(navDropDown);
    }

    @Step("Clicking on search icon")
    public void clickOnSearchIcon() {
        eleUtil.doClick(searchIcon);
    }

    @Step("Searching for the news")
    public void searchForNews() {
        String keyWord = excelUtil.getTestDataMethod("search");
        eleUtil.doSendKeys(searchBar, keyWord);
        eleUtil.doClick(goButton);
    }

    public int getListOfNews() {
        int count = eleUtil.getElementsTextListWithWait(newsCount, Constants.DEFAULT_TIME_OUT).size();
        return count;
    }

    public void scrollDownToFooter() {
        jsUtil.scrollPageDown();


    }

    public  List<String> getFooterHeadings(){
        List<String> footerHeadings = eleUtil.getElementsTextListWithWait(footerTitles,Constants.DEFAULT_TIME_OUT);
        return footerHeadings;
    }
    public String clickOnOtherLanguage() {
        eleUtil.doClick(langauage);
        return eleUtil.doElementGetText(langauage);
    }
}
