package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.kunitskaya.base.constants.AccountConstants.PASSWORD;
import static com.kunitskaya.base.constants.AccountConstants.USERNAME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HelpPopupTest extends BaseTest {

    LoginPageFactory loginPage = new LoginPageFactory(webDriver);
    BaseLoggedInPageFactory baseLoggedInPage = new BaseLoggedInPageFactory(webDriver);
    private static final String SEARCH_INPUT = "Change";

    @Test(description = "Log in to Gmail")
    public void logIn() {

        loginPage.open().fillInUsername(USERNAME).clickUsernameNextButton();
        assertEquals(loginPage.getUsernameValue(), USERNAME);

        loginPage.fillInPassword(PASSWORD).clickPasswordNextButton();
        assertTrue(baseLoggedInPage.isLoggedInAccountIconVisible());
    }

    @Test(description = "CDP-0003 Gmail: Help pop-up", dependsOnMethods = "logIn")
    public void validateHelpPopup(){
        HelpPopupFactory helpPopup =  baseLoggedInPage.clickSettingsButton().clickHelpSettingsOption();
        assertTrue(helpPopup.isHelpPopupDisplayed());

        helpPopup.enterTextToHelpSearchField(SEARCH_INPUT);
        List<WebElement> searchResults = helpPopup.getSearchResults();

        for(WebElement searchResult : searchResults){
           String result =  searchResult.getText();
            assertTrue(StringUtils.containsIgnoreCase(result, SEARCH_INPUT));
        }

        helpPopup.clearSearchField();

        GmailHelpPageFactory gmailHelpPage = helpPopup.clickBrowseAllArticlesLink();
        String w = webDriver.getWindowHandle();
        webDriver.switchTo().window(w).close();
    }
}

