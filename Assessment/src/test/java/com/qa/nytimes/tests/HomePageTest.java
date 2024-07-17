package com.qa.nytimes.tests;

import com.qa.nytimes.base.BaseTest;
import com.qa.nytimes.utils.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Epic - 100  - New York Editorial Site")
@Story("US 101 - Validating Functionalities")
public class HomePageTest extends BaseTest {

    @Test
    @Description("Login page title test....")
    @Severity(SeverityLevel.NORMAL)
    public void homePageTitleTest() {
        String nyPageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(nyPageTitle, Constants.HOME_PAGE_TITLE);
    }
    @Test
    @Description("Check for login button link....")
    @Severity(SeverityLevel.CRITICAL)
    public void homeButtonLinkTest() {
        Assert.assertTrue(homePage.isLoginLinkExist());
    }

    @Test
    @Description("Check for US news..")
    public void validatingUsNewsLinkTest(){
        homePage.clickOnUSNav();
        String usPageURL = homePage.getUSPageURL();
        Assert.assertTrue(usPageURL.contains(Constants.US_PAGE_URL));
    }


    @Test
    @Description("Validating the search functionality..")
    public void validateSearchFunctionalityTest(){
        homePage.clickOnSearchIcon();
        homePage.searchForNews();
        int newsCount = homePage.getListOfNews();
        Assert.assertNotEquals(newsCount,0);
    }

    @Test
    @Description("Validating footer existence.")
    public void validatingFooterTest(){
        homePage.scrollDownToFooter();
        List<String> footerHeadings = homePage.getFooterHeadings();
        Assert.assertEquals(footerHeadings,Constants.FOOTERLIST);
    }


    @Test
    @Description("Validating language translation")
    public void validateLanguageTranslationTest(){
        String language = homePage.clickOnOtherLanguage();
        Assert.assertEquals(language,Constants.LANGUAGE_SELECTED);
    }

}

