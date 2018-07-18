package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
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

    @Test(description = "log in to Gmail")
    public void logIn() {

        loginPage.open().fillInUsername(USERNAME).clickUsernameNextButton();
        assertEquals(loginPage.getUsernameValue(), USERNAME);

        loginPage.fillInPassword(PASSWORD).clickPasswordNextButton();
        assertTrue(baseLoggedInPage.isLoggedInAccountIconVisible());
    }

    @Test(description = "Open Help popup and test it", dependsOnMethods = "logIn")
    public void validateHelpPopup(){
        HelpPopupFactory helpPopup =  baseLoggedInPage.clickSettingsButton().clickHelpSettingsOption();
        assertTrue(helpPopup.isHelpPopupDisplayed());

        helpPopup.enterTextToHelpSearchField("Change");
        List<WebElement> searchResults = helpPopup.getSearchResults();

        for(WebElement searchResult : searchResults){
            assertTrue(searchResult.getText().contains("Change"));
        }
    }
}

