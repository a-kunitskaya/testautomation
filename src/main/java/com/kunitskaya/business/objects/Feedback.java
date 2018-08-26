package com.kunitskaya.business.objects;

import java.util.Objects;

public class Feedback {
    private String feedbackText;
    private boolean isWithScreenshot;

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public boolean isWithScreenshot() {
        return isWithScreenshot;
    }

    public void setWithScreenshot(boolean withScreenshot) {
        isWithScreenshot = withScreenshot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return isWithScreenshot == feedback.isWithScreenshot &&
                Objects.equals(feedbackText, feedback.feedbackText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(feedbackText, isWithScreenshot);
    }
}
