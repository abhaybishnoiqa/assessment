package com.qa.nytimes.tests;

import com.qa.nytimes.base.BaseTest;
import com.qa.nytimes.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageTitleTest() {
        String nyPageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(nyPageTitle, Constants.HOME_PAGE_TITLE);
    }
    
    @Test
    public void homeButtonLinkTest() {
        Assert.assertTrue(homePage.isLoginLinkExist());
    }

    @Test
    public void validatingUsNewsLinkTest(){
        homePage.clickOnUSNav();
        String usPageURL = homePage.getUSPageURL();
        Assert.assertTrue(usPageURL.contains(Constants.US_PAGE_URL));
    }


    @Test
    public void validateSearchFunctionalityTest(){
        homePage.clickOnSearchIcon();
        homePage.searchForNews();
        int newsCount = homePage.getListOfNews();
        Assert.assertNotEquals(newsCount,0);
    }

    @Test
    public void validatingFooterTest(){
        homePage.scrollDownToFooter();
        List<String> footerHeadings = homePage.getFooterHeadings();
        Assert.assertEquals(footerHeadings,Constants.FOOTERLIST);
    }


    @Test
    public void validateLanguageTranslationTest(){
        String language = homePage.clickOnOtherLanguage();
        Assert.assertEquals(language,Constants.LANGUAGE_SELECTED);
    }

}

