package com.kunitskaya.pf;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class ComposeEmailPopup extends AbstractPage {

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement toField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement subjectField;

    public ComposeEmailPopup() {
        super();
    }

    public ComposeEmailPopup fillInToField(String to) {
        waitForElementVisibility(webDriver, toField);
        new Actions(webDriver).click(toField).sendKeys(to).sendKeys(Keys.TAB).build().perform();
        return this;
    }

    public ComposeEmailPopup sendEmailWithHotkeys() {
        new Actions(webDriver).click(subjectField)
                              .sendKeys(Keys.chord(IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL, Keys.ENTER))
                              .build()
                              .perform();
        return this;
    }
}
