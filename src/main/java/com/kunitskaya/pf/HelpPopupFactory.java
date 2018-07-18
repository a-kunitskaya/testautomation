package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HelpPopupFactory extends BaseLoggedInPageFactory {

    @FindBy(xpath = "//div[@class='ghp-header-row']")
    WebElement helpPopup;

    @FindBy(id = "search-box")
    WebElement helpSearchField;

    @FindAll(@FindBy(css = ".ghp-iconTextComponent-label.ng-binding")) //*[@class="ghp-iconTextComponent-label ng-binding"]
    List<WebElement> searchResults;

    protected HelpPopupFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isHelpPopupDisplayed() {
        if (helpPopup.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public HelpPopupFactory enterTextToHelpSearchField(String input){
        helpSearchField.click();
        helpSearchField.sendKeys(input);
        return this;
    }

    public List<WebElement> getSearchResults(){
        return searchResults;
    }

}
