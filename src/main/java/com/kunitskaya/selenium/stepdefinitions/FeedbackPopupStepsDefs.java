package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.selenium.pages.pf.FeedbackPopup;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.kunitskaya.selenium.pages.pf.FeedbackPopup.FEEDBACK_POPUP_HEADER;
import static com.kunitskaya.selenium.pages.pf.FeedbackPopup.FEEDBACK_POPUP_PLACEHOLDER;
import static org.testng.Assert.*;

public class FeedbackPopupStepsDefs {

    @And("^I open feedback popup$")
    public void iOpenFeedbackPopup() {
        NavigationOperations.goToFeedbackPopup();
    }

    @Then("^Feedback popup is displayed$")
    public void feedbackPopupIsDisplayed() {
        assertTrue(new FeedbackPopup().isSendFeedbackPopupDisplayed());
        assertTrue(new FeedbackPopup().isInputFieldDisplayed());
        assertTrue(new FeedbackPopup().isCancelButtonDisplayed() && new FeedbackPopup().isSendButtonDisplayed());
        assertEquals(new FeedbackPopup().getHeader(), FEEDBACK_POPUP_HEADER);
        assertEquals(new FeedbackPopup().getInputFieldPlaceholder(), FEEDBACK_POPUP_PLACEHOLDER);
        assertEquals(new FeedbackPopup().isIncludeScreenshotCheckboxChecked(), TestDataProvider.getDefaultFeedback().isWithScreenshot());

    }

    @When("^I leave feedback and cancel it$")
    public void iLeaveFeedbackAndCancelIt() {
        UserOperations.leaveFeedback(TestDataProvider.getDefaultFeedback(), true);
    }

    @Then("^Feedback popup is not displayed$")
    public void feedbackPopupIsNotDisplayed() {
        assertFalse(new FeedbackPopup().isSendFeedbackPopupDisplayed());
    }

}
