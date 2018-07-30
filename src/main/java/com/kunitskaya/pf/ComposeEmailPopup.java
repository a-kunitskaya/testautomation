package com.kunitskaya.pf;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;

public class ComposeEmailPopup extends AbstractPage {

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement toField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement subjectField;

    public ComposeEmailPopup(WebDriver webDriver) {
        super(webDriver);
    }

    public ComposeEmailPopup fillInToField(String to) throws IOException {
        waitForElementVisibility(webDriver, toField);
        new Actions(webDriver).click(toField).sendKeys(to).build().perform();
        new Actions(webDriver).sendKeys(Keys.TAB).build().perform();
        return this;
    }

    public ComposeEmailPopup sendEmailWithHotkeys() {
        new Actions(webDriver).click(subjectField).build().perform();
        if (SystemUtils.IS_OS_MAC) {
            new Actions(webDriver).sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER)).build().perform();
        } else {
            new Actions(webDriver).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER)).build().perform();
        }
        return this;
    }
}
